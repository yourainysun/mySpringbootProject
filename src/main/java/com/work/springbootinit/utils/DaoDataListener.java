package com.work.springbootinit.utils;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.work.springbootinit.model.entity.DaoData;

import lombok.extern.slf4j.Slf4j;


import com.alibaba.excel.read.listener.ReadListener;

import java.util.List;

@Slf4j
// DaoDataListener 不能被spring管理，每次读取excel都需要new一个对象，可以用构造方法将spring中的对象传入到本类方法中
public class DaoDataListener implements ReadListener<DaoData> {
    // 一次内存中加载的数据量
    private static final int BATCH_COUNT = 100;
    // 内存临时缓存的数据
    private List<DaoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private DaoData daoData;
    // 通过构造方法传入spring管理的对象
    public DaoDataListener(DaoData daoData){
        this.daoData = daoData;
    }
    @Override
    public void invoke(DaoData daoData, AnalysisContext context){
        log.info("解析一条数据：{}", JSON.toJSONString(daoData));
        cachedDataList.add(daoData);
        // 达到BATCH_COUNT上限后，需要将缓存数据持久化到数据库中保存
        if(cachedDataList.size() >= BATCH_COUNT){
            saveData();
            // 存储完成清理 List 只要让原来的ArrayList没有引用对象，就会被垃圾回收内存
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    // 将最终cachedDataList中缓存数据存储到数据库中
    @Override
    public void doAfterAllAnalysed(AnalysisContext context){
        saveData();
        log.info("所有数据解析完成");
    }

    private void saveData(){
        log.info("{}条数据， 开始存储数据库", cachedDataList.size());
        daoData.save(cachedDataList);
        log.info("存储数据库成功");
    }


}





