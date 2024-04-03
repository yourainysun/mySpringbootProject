package com.work.springbootinit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonUtils {
    // java调用Python文件通用方法
    public static void callPython(String pythonPath, String[] args) {
        try {
            // 创建一个进程
            Process process = Runtime.getRuntime().exec(pythonPath + " " + String.join(" ", args));
            // 获取进程的标准输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
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
    }

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


}
