package com.work.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * 
     */
    @TableId
    private String roleCode;

    /**
     * 
     */
    private String tenantCode;

    /**
     * 
     */
    private String roleName;

    /**
     * 
     */
    private String creator;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private String removeYn;

    /**
     * 
     */
    private Integer showOrder;

    /**
     * 
     */
    private String branCode;

    /**
     * 
     */
    private String createDt;

    /**
     * 
     */
    private String createBy;

    /**
     * 
     */
    private String modifyDt;

    /**
     * 
     */
    private String modifyBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}