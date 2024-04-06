package com.work.springbootinit.utils;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * access 指明了创建的无参构造函数的访问是私有的
 * 限制外部直接实例化该类，需要通过类内部声明或类的静态工厂实例化
 */
@Slf4j(topic = "PythonExecutor")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class PythonUtils implements InitializingBean {
    @Value("E:\\PYTHON\\file\\myPythonProject\\mytest\\sentenceTransformer")
    private String pythonUrlTmp;

    @Value("${pythonWebUrl:}")
    private String pythonWebUrlTmp;

    public static String pythonUrl;

    public static String pythonWebUrl;

    // // java调用Python文件通用方法,返回结果
    public static String callPython(String pythonPath, String[] args) {
        StringBuilder result = new StringBuilder();
        try {
            // 创建一个进程
            Process process = Runtime.getRuntime().exec(pythonPath + " " + String.join(" ", args));
            // 获取进程的标准输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
            // 获取进程的标准错误流
            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = err.readLine()) != null) {
                System.out.println(line);
            }
            err.close();
            // 等待进程执行完毕
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    //实现initializingBean类方法,在bean初始化后可以自定义方法实现初始化
    @Override
    public void afterPropertiesSet() throws IOException {
        pythonUrl = pythonUrlTmp;
        pythonWebUrl = pythonWebUrlTmp;
    }

    /**
     * 方法的重载，使得用户不填url时，执行默认设置
     * @param functionNo
     * @param pythonParam
     * @return
     */
    public static String executePythonWeb(String functionNo, String[] pythonParam) throws IOException{
        return executePythonWeb(pythonWebUrl, functionNo, pythonParam);
    }

    public static String executePython(String functionNo, String[] pythonParam) throws IOException {
        return executePython(pythonUrl, functionNo, pythonParam);
    }

    /**
     * python方法调用
     *
     *
     * @param pythonUrl
     * @param functionNo
     * @param pythonParam
     * @return
     * @throws IOException
     */
    public static String executePython(String pythonUrl, String functionNo, String[] pythonParam) throws IOException{
        String url = pythonUrl + functionNo;
        if(!pythonUrl.endsWith("/") && !functionNo.startsWith("/")){
            url = pythonUrl + "/" + functionNo;
        }
        String[] args = new String[pythonParam.length + 2];
        args[0] = "python";
        args[1] = url;
        System.arraycopy(pythonParam, 0, args, 2, pythonParam.length);

        log.info("--------------------------executePython pythonUrl:["
        + url + "]--------------------------");

        try{
            String r = RuntimeUtil.execForStr(args);
            if (StrUtil.isEmpty(r)){
                log.error("executePython error"+ Arrays.toString(args));
                throw new Exception(
                        "executePython pythonUrl:[" + url + "];"
                        + "executePython pythonParam:[" + pythonParam + "];"
                        + "executePython error:" + Arrays.toString(args));
            }
            log.info("--------------------executePython return:[" + r + "]-------------------");
            return r;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * web接口调用python
     *
     */
    public static String executePythonWeb(String pythonWebUrl, String functionNo, String[] pythonParam) throws IOException{
        if(StrUtil.isBlank(pythonWebUrl)){
            return executePython(functionNo, pythonParam);
        }
        if(functionNo.endsWith(".py")){
            functionNo = StrUtil.sub(functionNo, 0, -3);

        }
        String url = pythonWebUrl + functionNo;
        if (!pythonWebUrl.endsWith("/") && !functionNo.startsWith("/")){
            url = StrUtil.sub(pythonWebUrl, 0, -1) + functionNo;
        }
        log.info("-----------------------executePython pythonUrl:[" + url + "]---------------------");
        log.info("--------------------executePython pythonParam:[" + pythonParam + "]-------------------------------");
        String r = HttpUtil.post(url, pythonParam[0]);
        if (StrUtil.isEmpty(r)){
            throw new RuntimeException(
                    "executePython pythonUrl:[" + url + "];"
                            + "executePython pythonParam:[" + pythonParam + "];");
        }
        log.info("-----------------executePython return:[" + r + "]-------------------------");
        return r;
    }



}
