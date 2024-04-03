package com.work.springbootinit.utils;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.util.List;

public class JiebaUtils {
    private static void initJieba() {
        // 初始化 Jieba
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<String> strings = segmenter.sentenceProcess("移动与您共筑十全十美");
        for (String string : strings) {
            System.out.println(string);
            // 相似词获取
            // 通过pythonUtil调用python文件，返回List相似词
            // List<String> similarWords = PythonUtil.getSimilarWords(string);
        }
    }

    public static List<String> cut(String sentence){
        JiebaSegmenter segmenter = new JiebaSegmenter();
        return segmenter.sentenceProcess(sentence);
    }

    public static void main(String[] args) {
        initJieba();
    }
}
