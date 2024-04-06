package com.work.springbootinit.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.springbootinit.mapper.IndexMapper;
import com.work.springbootinit.mapper.VideoMapper;
import com.work.springbootinit.model.entity.Index;
import com.work.springbootinit.model.entity.Video;
import com.work.springbootinit.service.IndexService;
import com.work.springbootinit.utils.JiebaUtils;
import com.work.springbootinit.utils.PythonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.json.Json;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IndexServiceImpl extends ServiceImpl<IndexMapper, Index> implements IndexService{
    @Resource
    private IndexMapper indexMapper;
    @Resource
    private VideoMapper videoMapper;

    @Override
    public void buidIndex() {
        // 1. 从数据库中查询所有的数据,分页搜索，一次1000条
            //先获取总记录数
        Long count = videoMapper.selectCount(null);
        final Long pageSize = 1000L;
        Long pageCount = count / pageSize;
        if (count % pageSize != 0){
            pageCount++;
        }


//        for (int i = 0; i < pageCount; i++){
//            Page<Video> page = new Page<>(i, pageSize);
//
//            IPage<Video> indexPage = videoMapper.selectPage(page, null);
//            List<Video> records = indexPage.getRecords();
//            for (Video record : records){
//                //分词
//                String title = record.getTitle();
//                // jieba分词
//                List<String> words = JiebaUtils.cut(title);
//                //对每个分词进行查询相似词
//                for (String word : words) {
//                    PythonUtils.executePython();
//                }
//                System.out.println(words);
        // 测试
        Page<Video> page = new Page<>(1, pageSize);
        IPage<Video> indexPage = videoMapper.selectPage(page, null);
        List<Video> records = indexPage.getRecords();
        String title = records.get(0).getTitle();
        List<String> words = JiebaUtils.cut(title);

        String[] args = new String[words.size()];
        String pythonFile = "cosine_score_calculate.py";

        for (int i = 0; i < words.size(); i++) {
            args[i] = words.get(i);
        }
        try{
            String result = PythonUtils.executePython(pythonFile, args);
            log.info("result: {}", result);
            //将result 字符串转为json对象
            JSONObject jsonObject = new JSONObject(result);
            // json对象转map
            Map map = jsonObject.toBean(Map.class);


            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }




//                //保存到数据库
//                Index index = new Index();
//                index.setBvid(record.getBvid());
//                index.setTitle(title);
//                indexMapper.insert(index);
    }
        // 2. 遍历数据，对每一条数据进行分词
        // 3. 将分词结果保存到数据库中

}
