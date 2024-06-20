package com.work.springbootinit.model.dto.file;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Excel2SqlDto {
//    @ExcelProperty(index = 0)
//    private String userCode;
//
//    @ExcelProperty(index = 1)
//    private String number;
//
//    @ExcelProperty(index = 2)
//    private String roleName;
    @ExcelProperty(index = 0)
    private String userCode;

    @ExcelProperty(index = 1)
    private String roleName;
}
