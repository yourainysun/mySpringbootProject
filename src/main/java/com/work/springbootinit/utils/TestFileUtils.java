package com.work.springbootinit.utils;

public class TestFileUtils {

    public static String getPath() {
        return TestFileUtils.class.getResource("/").getPath();
    }
}
