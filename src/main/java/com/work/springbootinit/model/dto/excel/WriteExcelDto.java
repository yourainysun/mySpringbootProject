package com.work.springbootinit.model.dto.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class WriteExcelDto {
    @ExcelProperty("userCode")
    private String userCode;

    @ExcelProperty("roleName")
    private String roleName;
}
