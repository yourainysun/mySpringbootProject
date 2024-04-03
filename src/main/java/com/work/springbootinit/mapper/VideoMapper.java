package com.work.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.work.springbootinit.model.dto.video.VideoTitleDto;
import com.work.springbootinit.model.entity.Video;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {
    List<VideoTitleDto> selectTitleList();

    List<VideoTitleDto> selectTitleListByPage(int pageNum, Long pageSize);
}
