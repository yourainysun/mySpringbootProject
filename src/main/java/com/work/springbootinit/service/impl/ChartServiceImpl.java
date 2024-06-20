package com.work.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.work.springbootinit.mapper.ChartMapper;
import com.work.springbootinit.model.entity.Chart;
import com.work.springbootinit.service.ChartService;
import org.springframework.stereotype.Service;

/**
* @author sjf123
* @description 针对表【chart(图表)】的数据库操作Service实现
* @createDate 2023-12-14 17:01:51
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService {

}




