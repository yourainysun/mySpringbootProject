package com.work.springbootinit.model.dto.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ReadExcelDto {
    @ExcelProperty(index=0)
    private String userCode;

    @ExcelProperty(index=1)
    private String roleName;
}
