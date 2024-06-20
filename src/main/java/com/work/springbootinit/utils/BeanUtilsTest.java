package com.work.springbootinit.utils;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.work.springbootinit.model.dto.UserBaseInfoDto;
import com.work.springbootinit.model.dto.user.UserAddRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtilsTest {
    public static void main(String[] args) {
//        List<UserAddRequest> userAddRequests = new ArrayList<>();
//        List<UserBaseInfoDto> userBaseInfoDtos = new ArrayList<>();
//
//        UserAddRequest userAddRequest = new UserAddRequest();
//        userAddRequest.setUserName("陈哥");
//        userAddRequest.setUserAccount("haha");
//        userAddRequests.add(userAddRequest);
//        userAddRequest = new UserAddRequest();
//        userAddRequest.setUserName("马哥");
//        userAddRequest.setUserAccount("bbaba");
//
//        userAddRequests.add(userAddRequest);
//
////        for (UserAddRequest addRequest : userAddRequests) {
////            System.out.println(addRequest.getUserName());
////        }
//
//        userBaseInfoDtos = BeanUtil.copyToList(userAddRequests, UserBaseInfoDto.class );
//
////        userBaseInfoDtos = copy(userAddRequests, UserBaseInfoDto.class);
//
//        for (UserBaseInfoDto userBaseInfoDto : userBaseInfoDtos) {
//            System.out.println(userBaseInfoDto.getUserName());
//
////        }
//        String a = "asdg";
//        String b = "1234";
//        List<String> list = new ArrayList<>();
//        list.add(a);
//        list.add(b);
//        List<String> newList = list.stream().filter(o -> !o.matches("\\d+")).collect(Collectors.toList());
//        System.out.println(newList);

        List<String> a = new ArrayList<>();
        System.out.println(a.size());
    }

    static class testEntity{
        private String str1;
        private String userName;
    }


        /**
         * 从List<A> copy到List<B>
         * @param list List<B>
         * @param clazz B
         * @return List<B>
         */
        public static <T> List<T> copy(List<?> list,Class<T> clazz){
            String oldOb = JSON.toJSONString(list);
            return JSON.parseArray(oldOb, clazz);
        }

        /**
         * 从对象A copy到 对象B
         * @param ob A
         * @param clazz B.class
         * @return B
         */
        public static <T> T copy(Object ob,Class<T> clazz){
            String oldOb = JSON.toJSONString(ob);
            return JSON.parseObject(oldOb, clazz);
        }

}
