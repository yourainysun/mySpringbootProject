package com.work.springbootinit.model.entity;
import lombok.Data;

import java.io.Serializable;


@Data
public class Index implements Serializable {

  private Long id;
  private String word;
  private String relevantWord;
  private String bvid;

}
