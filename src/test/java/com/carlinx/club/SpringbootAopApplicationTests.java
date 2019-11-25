package com.carlinx.club;

import cn.hutool.json.JSONUtil;
import com.carlinx.club.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootAopApplicationTests {

    @Autowired
    private HelloService helloService;

    @Test
    public void contextLoads() {
        Map hello = helloService.hello("yj", 23, "男");
        System.out.println(hello);
    }


    @Test
    public void testArray(){
        List<Object> objects = Arrays.asList();
        System.out.println(JSONUtil.toJsonStr(objects));
    }


}
