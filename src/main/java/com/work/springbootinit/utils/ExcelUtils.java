package com.work.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.work.springbootinit.exception.ThrowUtils;
import com.work.springbootinit.mapper.SysRoleMapper;
import com.work.springbootinit.model.dto.excel.ReadExcelDto;
import com.work.springbootinit.model.dto.excel.ReturnDto;
import com.work.springbootinit.model.dto.excel.WriteExcelDto;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.work.springbootinit.common.ErrorCode.NOT_FOUND_ERROR;

/***
 * Excel 相关工具类
 */
@Slf4j
public class ExcelUtils {
    @Autowired
    private static SysRoleMapper sysRoleMapper;

    public static String excelToCsv(MultipartFile multipartFile) {

        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync(); // 这是同步的返回
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }


//        try {
//            EasyExcel.read(multipartFile.getInputStream(), new ReadListener<Map<Integer, String>>() {
//                private static final int BATCH_COUNT = 100;
//                @Override
//                public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
//                    List<Map<Integer, String>> list = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//                    list.add(map);
//                    if(list.size() >= BATCH_COUNT){
//                        // 此处省略将数据保存入数据库中
//                        // saveData();
//                        list = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//                    }
//
//
//
//                }
//
//                @Override
//                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//                    //saveData();
//                }
//            }).sheet().doRead();
//        } catch (IOException e) {
//            log.error(String.valueOf(e));
//        }


        if (CollUtil.isEmpty(list)) {
            return "fail";
        }

        // 转换为csv
        LinkedHashMap<Integer, String> headerMap = (LinkedHashMap<Integer, String>) list.get(0);
        // 过滤掉空的表头
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        System.out.println(StringUtils.join(headerList, ","));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringUtils.join(headerList, ",")).append('\n');


        for (int i = 1; i < list.size(); i++) {
            LinkedHashMap<Integer, String> dataMap = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            System.out.println(StringUtils.join(dataList, ","));
            stringBuilder.append(StringUtils.join(dataList, ",")).append('\n');

        }
        System.out.println(StringUtils.join());
        return stringBuilder.toString();
    }

    public static ReturnDto excel2sql(String inputFileName) throws IOException{
        URL url = ExcelUtils.class.getClassLoader().getResource(inputFileName);
        ThrowUtils.throwIf(url==null, NOT_FOUND_ERROR, "文件不存在!");
        String inputFilePath = URLDecoder.decode(url.getPath(), "UTF-8");

        List<String> sqlList;
        Map<String, String> userRoleRelationMap = new HashMap<>();
        List<String> roleNameList = new ArrayList<>();

        EasyExcel.read(inputFilePath, ReadExcelDto.class, new PageReadListener<ReadExcelDto>(dataList -> {
            for (ReadExcelDto readExcelDto : dataList) {
                userRoleRelationMap.put(readExcelDto.getUserCode(), readExcelDto.getRoleName());
                roleNameList.add(readExcelDto.getRoleName());
            }
        })).sheet().doRead();

        ReturnDto returnDto = new ReturnDto();
        returnDto.setRoleNameList(roleNameList);
        returnDto.setUserRoleRelationMap(userRoleRelationMap);

        return returnDto;
    }


    public static void txt2Excel(String intputFileName, String outputFilePath) throws IOException{
//        File file = ResourceUtils.getFile("classpath:" + fileName);
        URL url = ExcelUtils.class.getClassLoader().getResource(intputFileName);
        ThrowUtils.throwIf(url==null, NOT_FOUND_ERROR, "文件不存在!");
        // url.getPath()会将特殊字符转义，通过decode，UTF-8将特殊字符再现
        String inputFilePath = URLDecoder.decode(url.getPath(), "UTF-8");
        EasyExcel.write(outputFilePath, WriteExcelDto.class).sheet("模版").doWrite(writeTxt2Dto(inputFilePath));
    }

    public static List<WriteExcelDto> writeTxt2Dto(String filePath){
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader);
        ){
            String line;
            List<WriteExcelDto> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\\s+");
                WriteExcelDto writeExcelDto = new WriteExcelDto();
                writeExcelDto.setUserCode(strings[0]);
                writeExcelDto.setRoleName(strings[2]);

                list.add(writeExcelDto);
            }
            return list;
        } catch (IOException e) {
            log.error("文件读取问题：", e);
            return new ArrayList<>();
        }
    }


    public static void main(String[] args) {
        excelToCsv(null);
    }
}
