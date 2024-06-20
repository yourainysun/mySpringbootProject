package com.work.springbootinit.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.work.springbootinit.model.dto.file.Excel2SqlDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Excel2SqlListener implements ReadListener<Excel2SqlDto> {
    private static final int BATCH_COUNT = 100;

    private List<Excel2SqlDto> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private Excel2SqlDto excel2SqlDto;

    public Excel2SqlListener(){
        excel2SqlDto = new Excel2SqlDto();
    }

    public Excel2SqlListener(Excel2SqlDto excel2SqlDto){
        this.excel2SqlDto = excel2SqlDto;
    }


    @Override
    public void invoke(Excel2SqlDto data, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(data));
        cachedDataList.add(data);

        if(cachedDataList.size() >= BATCH_COUNT){
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有的数据解析完成！");
    }

    public void saveData(){
        log.info("{}条数据开始存储至数据库！", cachedDataList.size());
        log.info("数据存储完成！");
    }
}
