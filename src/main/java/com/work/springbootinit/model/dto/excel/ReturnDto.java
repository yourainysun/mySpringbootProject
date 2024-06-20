package com.work.springbootinit.model.dto.excel;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ReturnDto {
    private Map<String, String> userRoleRelationMap;
    private Map<String, String> roleName2roleCodeMap;
    private List<String> roleNameList = new ArrayList<>();
}
