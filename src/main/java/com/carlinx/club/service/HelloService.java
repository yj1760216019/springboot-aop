package com.carlinx.club.service;

import com.carlinx.club.annotation.RecordLog;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yj
 * @date: 2019/11/25 0025  10:51
 */

@Service
public class HelloService {

    @RecordLog
    public Map hello(String name,Integer age,String sex){
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        map.put("sex",sex);
        return map;
    }
}
