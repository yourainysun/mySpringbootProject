package com.work.springbootinit.model.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DaoData {
    private String string;
    private Date date;
    private Double doubleData;

    public void save(List<DaoData> list){

    }
}
