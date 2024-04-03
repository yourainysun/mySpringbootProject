package com.work.springbootinit.model.dto.chart;

import com.baomidou.mybatisplus.annotation.TableField;
import com.work.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChartQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 分析目标
     */
    private String goal;
    /**
     * 图标名称
     */
    private String name;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 创建用户 id
     */
    private Long userId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}