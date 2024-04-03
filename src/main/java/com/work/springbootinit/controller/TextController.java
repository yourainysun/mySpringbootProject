package com.work.springbootinit.controller;

import com.work.springbootinit.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/text")
@RestController
public class TextController {
    @Autowired
    private IndexService indexService;

    //实现索引的构建
    @PostMapping("/buidIndex")
    public void buidIndex() {
        // 1. 从数据库中查询所有的数据
        // 2. 遍历数据，对每一条数据进行分词
        // 3. 将分词结果保存到数据库中
        indexService.buidIndex();

    }

}
