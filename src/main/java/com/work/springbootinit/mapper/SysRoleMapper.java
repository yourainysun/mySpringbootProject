package com.work.springbootinit.mapper;

import com.work.springbootinit.model.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    @MapKey(value = "role_name")
    List<Map<String, String>> getRoleName2RoleCode(@Param("list") List<String> roleNameList);
}




