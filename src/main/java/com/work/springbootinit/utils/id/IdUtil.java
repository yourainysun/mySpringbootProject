package com.work.springbootinit.utils.id;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Slf4j
public class IdUtil {
    public static void main(String[] args) {
        System.out.println(IdUtil.getId());
    }

    private static SecureRandom random = new SecureRandom();

    /**
     * 获取IdWork实例
     *
     * @return
     */
    private static IdWorker getIdWorker() {
        return IdWorkerHoler.INSTANCE;
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 获取long类型ID
     *
     * @return long 返回类型
     * @author huangxinmei
     * @date 2016年7月26日上午9:33:56
     */
    public static long getLongId() {
        return getIdWorker().nextId();
    }

    /**
     * 获取30位id
     *
     * @return String 返回类型
     * @author huangxinmei
     * @date 2016年7月26日上午9:34:22
     */
    public static String getId() {
        return String.valueOf(getLongId());
    }


    /**
     * 根据表达式生成n位数的随机数
     *
     * @param parttern
     * @return
     * @author zhengfh
     * @date 2020/6/29
     */
    public static String getUUID(int parttern) {
        //int machineId = 1;// 最大支持1-9个集群机器部署
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        char[] cs = new char[parttern];
        char c = 0;
        for (int i = parttern, j = 0; i-- > 0; ) {
            c = uuid.charAt(i);
            cs[j++] = c;
        }
        String uid = String.valueOf(cs);
        return uid;
    }

    /**
     * 得到n位的UUID-(数字)
     * 例子：16位随机数：parttern="%016d"
     *
     * @param
     * @return
     * @author zhengfh
     * @date 2020/6/30
     */
    public static String getUUIDNumber(String parttern) {
        // int machineId = 1;// 最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {// 有可能是负数
            hashCodeV = -hashCodeV;
        }
        return String.format(parttern, hashCodeV);
    }

    /**
     * 静态内部类实现的懒加载，ID生成器中的雪花算法单例
     */
    private static class IdWorkerHoler {
        private static IdWorker INSTANCE;

        static {
            try {
                Integer workId = Optional.ofNullable(System.getProperty("bi.id.startnum")).map(Integer::valueOf).orElse(null);
                if (Objects.isNull(workId)) {
                    //在不存系统启动参数强制指定时，采用redis自动占位的方式来做处理
                    workId = getRedisHoderWorkId();
                    log.info("最终应用的IdWork的序列：{}", workId);
                }
                INSTANCE = new IdWorker(workId);
            } catch (Exception e) {
                log.warn("IdUtil初始化占位异常，将默认只是占位为1", e);
                INSTANCE = new IdWorker(1);
            }

        }

        public static void resetWorkId(Integer id) {
            INSTANCE.setWorkerId(id);
        }

        public static Integer getRedisHoderWorkId() {
            int bit = 1 << IdWorker.WORKER_ID_BITS;
            //无法获取的情况随机给于一个机器
            Integer workdId = Integer.parseInt(IdUtil.getUUIDNumber("%04d")) % bit;
            log.info("由于上述获取工作ID异常将随机给与一个工作ID【{}】", workdId);
            return workdId;
        }

        /**
         * 获取ip地址
         *
         * @return
         * @throws UnknownHostException
         */
        private static Integer getWorkIdByIp() {
            return Optional.ofNullable(SystemUtil.getHostIp()).map(o -> cn.hutool.core.util.StrUtil.removeAll(o, StrUtil.DOT)).map(Long::parseLong)
                    .map(o -> o % (1 << IdWorker.WORKER_ID_BITS)).map(o -> Integer.valueOf(o.toString())).orElse(null);
        }

    }


}
