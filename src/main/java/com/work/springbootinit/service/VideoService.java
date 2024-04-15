package com.work.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.springbootinit.model.dto.video.VideoTitleDto;
import com.work.springbootinit.model.entity.Video;

import java.io.IOException;
import java.util.Map;

public interface VideoService extends IService<Video> {
    Map<String, Object> searchPageByTitleAndContent(int pageNo, int pageSize, VideoTitleDto videoTitleDto) throws IOException;
}
