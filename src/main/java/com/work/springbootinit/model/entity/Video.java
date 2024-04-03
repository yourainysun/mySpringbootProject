package com.work.springbootinit.model.entity;
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

  private Long id;
  private String bvid;
  private Long cid;
  private String title;
  private String uri;
  private Long view;
  private Long like;
  private Long danmaku;
  private Long ownerMid;
  private String ownerName;
  private Long favorite;
  private Long coin;
  private Long share;

}
