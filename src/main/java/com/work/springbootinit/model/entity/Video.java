package com.work.springbootinit.model.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video implements Serializable {
  @TableId
  private Long id;

  @TableField("bvid")
  private String bvid;

  @TableField("cid")
  private Long cid;

  @TableField("title")
  private String title;

  @TableField("uri")
  private String uri;

  @TableField("view")
  private Long view;

  @TableField("`like`")
  private Long like;

  @TableField("danmaku")
  private Long danmaku;

  @TableField("owner_mid")
  private Long ownerMid;

  @TableField("owner_name")
  private String ownerName;

  @TableField("favorite")
  private Long favorite;

  @TableField("coin")
  private Long coin;

  @TableField("share")
  private Long share;

}
