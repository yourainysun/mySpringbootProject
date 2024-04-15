package com.work.springbootinit.controller;

import com.work.springbootinit.model.dto.video.VideoTitleDto;
import com.work.springbootinit.service.IndexService;
import com.work.springbootinit.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequestMapping("/text")
@RestController
public class TextController {
    @Autowired
    private IndexService indexService;

    @Autowired
    private VideoService videoService;

    //实现索引的构建
    @PostMapping("/buidIndex")
    public void buidIndex() {
        // 1. 从数据库中查询所有的数据
        // 2. 遍历数据，对每一条数据进行分词
        // 3. 将分词结果保存到数据库中
        indexService.buidIndex();
    }

    @PostMapping("/search")
    @ApiOperation(value="分页搜索")
    public Map<String,Object> searchPageByTitle(@RequestParam(defaultValue = "1") int pageNo,
                                                          @RequestParam(defaultValue = "10") int pageSize,
                                                          @RequestBody VideoTitleDto videoTitleDto
    ) throws IOException {

        if(pageNo == 0){
            pageNo = 1;
        }
        if(pageSize == 0){
            pageSize = 1;
        }
//        if(videoTitleDto.getSortType() == null){
//            videoTitleDto.setSortType(1);
//        }
//        if(videoTitleDto.getSortType() !=2 && videoTitleDto.getSortType()!=3)
//            videoTitleDto.setSortType(1);
        return videoService.searchPageByTitleAndContent(pageNo,pageSize,videoTitleDto);
    }

}
