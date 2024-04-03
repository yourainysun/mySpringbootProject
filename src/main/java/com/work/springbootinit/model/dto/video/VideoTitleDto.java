package com.work.springbootinit.model.dto.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VideoTitleDto implements Serializable {
    @ApiModelProperty("视频id")
    private String bvid;

    @ApiModelProperty("视频标题")
    private String title;

}
