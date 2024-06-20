package com.work.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.springbootinit.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author sjf123
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-12-14 16:39:50
* @Entity com.yupi.springbootinit.model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    void insertUserListByMap(@Param("list") List<Map<String, String>> userMapList);

    void insertOrUpdate(@Param("list") List<User> list);

}




