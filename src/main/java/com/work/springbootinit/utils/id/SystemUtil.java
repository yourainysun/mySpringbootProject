package com.work.springbootinit.utils.id;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class SystemUtil {

    /**
     * @return String 返回类型
     * @throws
     * @Title: getHostIp
     * @Description: 获取本地IP
     */
    public static String getHostIp() {
        InetAddress netAddress = getInetAddress();
        if (null == netAddress) {
            return null;
        }
        String ip = netAddress.getHostAddress(); //get the ip address
        return ip;
    }

    public static InetAddress getInetAddress() {

        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            //unknown host!
        }
        return null;

    }

    /**
     * @return String 返回类型
     * @throws
     * @Title: getHostName
     * @Description: 获取本机机器名
     */
    public static String getHostName() {
        InetAddress netAddress = getInetAddress();
        if (null == netAddress) {
            return null;
        }
        String name = netAddress.getHostName(); //get the host address
        return name;
    }
}
