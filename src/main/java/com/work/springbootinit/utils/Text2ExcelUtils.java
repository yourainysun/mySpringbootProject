package com.work.springbootinit.utils;

import com.alibaba.excel.EasyExcel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class Text2ExcelUtils {
    public static <T> void text2Excel(String fileName, String separate, Class<T> clazz, String outputFilePath) throws Exception {
        List<T> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //
        Constructor<T> constructor = clazz.getConstructor(String[].class);
        String line;
        while((line = br.readLine()) != null) {
            String[] values = line.split(separate);
            T instance = constructor.newInstance((Object)values);
            data.add(instance);
        }
        EasyExcel.write(outputFilePath, clazz).sheet("模版").doWrite(data);
    }
}
