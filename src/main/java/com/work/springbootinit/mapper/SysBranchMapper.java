package com.work.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.springbootinit.model.entity.SysBranch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysBranchMapper extends BaseMapper<SysBranch> {

    List<SysBranch> selectParentBranchCodes(@Param("branchCodeList")List<String> branchCodeList);
}
