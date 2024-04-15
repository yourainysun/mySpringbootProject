package com.work.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.springbootinit.mapper.VideoMapper;
import com.work.springbootinit.model.dto.video.VideoTitleDto;
import com.work.springbootinit.model.entity.Video;
import com.work.springbootinit.service.VideoService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Map<String, Object> searchPageByTitleAndContent(int pageNo, int pageSize, VideoTitleDto videoTitleDto) throws IOException {
        return null;
//        if(pageNo<=1){
//            pageNo=1;
//        }
//        Integer sortType = videoTitleDto.getSortType();
//
//        //条件搜索
//        SearchRequest searchRequest = new SearchRequest("knowledge");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        //分页
//        searchSourceBuilder.from((pageNo-1)*pageSize);
//        searchSourceBuilder.size(pageSize);
//
//        //模糊匹配
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//
//        boolQueryBuilder.mustNot(QueryBuilders.termQuery("status","0"));
//        if(videoTitleDto.getKeyword() != null){
//            boolQueryBuilder.should(QueryBuilders.matchQuery("title",videoTitleDto.getKeyword()));
//            boolQueryBuilder.should(QueryBuilders.matchQuery("content",videoTitleDto.getKeyword()));
//        }
//        if(videoTitleDto.getKindName()!=null){
//            QueryWrapper<Kind> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("kind_name",videoTitleDto.getKindName());
//            Kind kind = kindService.getOne(queryWrapper);
//
//            boolQueryBuilder.must(QueryBuilders.termQuery("kind_id",kind.getKindId()));
//        }
//
//
//        if(videoTitleDto.getRangeTime() !=null){
//            List<Date> Timelist = videoTitleDto.getRangeTime();
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("update_time").from(Timelist.get(0)).to(Timelist.get(1)));
//        }
//        if(videoTitleDto.getNickName()!=null){
//            boolQueryBuilder.must(QueryBuilders.termQuery("nick_name.keyword",videoTitleDto.getNickName()));
//        }
//        if(videoTitleDto.getTagName()!=null){
//            List<String> tagName = videoTitleDto.getTagName();
//            for (String s : tagName) {
//                boolQueryBuilder.should(QueryBuilders.termQuery("tag_name",s));
//            }
//        }
////        boolQueryBuilder.minimumShouldMatch(1);
//
//        if(sortType == 1){
//            //按照相关性排序
//            searchSourceBuilder.query(boolQueryBuilder);
//        }else if(sortType ==2){
//            //按照最新时间排序
//            searchSourceBuilder.query(boolQueryBuilder).sort("update_time", SortOrder.DESC);
//        }else if(sortType==3){
//            //按照最热排序
//            searchSourceBuilder.query(boolQueryBuilder).sort("view_counts",SortOrder.DESC);
//        }else{
//            throw new ServiceException(Constants.CODE_500,"type输入有误");
//        }
//        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//
//        //高亮关键词
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("title");//高亮字段
//        highlightBuilder.field("content");
//        highlightBuilder.requireFieldMatch(true);//true,多个相同关键词均高亮
//        highlightBuilder.preTags("<span style='color:red'>");
//        highlightBuilder.postTags("</span>");
//        searchSourceBuilder.highlighter(highlightBuilder);
//
//        //执行搜索
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        //解析结果
//        ArrayList<Map<String,Object>> list = new ArrayList<>();
//        int totalHit = (int)searchResponse.getHits().getTotalHits().value;
//
//        for (SearchHit hit : searchResponse.getHits().getHits()) {
//            //获取高亮字段
//            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//            HighlightField title = highlightFields.get("title");
//
//            Map<String,Object> sourceAsMap = hit.getSourceAsMap();
//
//            //解析高亮的字段
//            if(title!=null){
//                Text[] fragments = title.fragments();
//                String new_title = "";
//                for(Text text : fragments){
//                    new_title += text;
//                }
//                sourceAsMap.put("title",new_title);
//            }
//            //该方法没用，es高亮显示是直接处理hit中的content，将处理好的文本put进map中后，hit还是会覆盖处理后的文本，需要在传入es时就处理成文本
////            //转换正文富文本html格式为文本格式
////            Object test = sourceAsMap.get("content");
////            String contentText = String.valueOf(test);
////            sourceAsMap.put("content",Html2TextUtil.getContent(contentText));
//
//            HighlightField content = highlightFields.get("content");
//            //解析高亮的字段
//            if(content!=null){
//                Text[] fragments = content.fragments();
//                String new_content = "";
//                for(Text text : fragments){
//                    new_content += text;
//                }
//                sourceAsMap.put("content",new_content);
//            }
//            list.add(sourceAsMap);
//
//
//        }
//        Map<String,Object> map = new HashMap<>();
//        map.put("data",list);
//        map.put("total",totalHit);
//
//
//        return map;

    }
}
