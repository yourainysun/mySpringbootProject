package com.work.springbootinit.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBaseInfoDto extends BasePageDto implements Serializable {
    private String userName;
}
