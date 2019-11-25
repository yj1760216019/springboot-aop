package com.carlinx.club.aspect;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yj
 * @date: 2019/11/25 0025  10:10
 */

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    //制定切面
    @Pointcut("@annotation(com.carlinx.club.annotation.RecordLog)")
    public void logPointCut(){}


    /**
     * 切面环绕通知采集日志
     * @param proceedingJoinPoint
     * @return
     */
    @Around("logPointCut()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint){
        //获取系统时间
        Long beginTime = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        //获取当前方法
        Method method = methodSignature.getMethod();
        //获取方法名
        String methodName = method.getName();
        //获取类名
        String className = method.getDeclaringClass().getName();
        //获取参数名
        List<Parameter> parameterNames = Arrays.asList(method.getParameters());
        //获取参数
        List<Object> paramters = Arrays.asList(proceedingJoinPoint.getArgs());
        Map<String, Object> paramterMap = new HashMap<>();
        for (int i = 0;i < parameterNames.size();i++){
            paramterMap.put(parameterNames.get(i).getName(),paramters.get(i));
        }
        //获取方法执行结果
        Object result = null;
        Long consumTime = null;
        try {
            result = proceedingJoinPoint.proceed();
        }catch (Throwable throwable) {
            consumTime = System.currentTimeMillis() - beginTime;
            logger.info("类名:{}   方法名:{}   参数:{}   执行结果:{}   总耗时:{}",className,methodName, JSONUtil.toJsonStr(paramterMap),JSONUtil.toJsonStr(result),consumTime);
            throwable.printStackTrace();
        }
        //获得耗时时间
        consumTime = System.currentTimeMillis() - beginTime;
        logger.info("类名:{}   方法名:{}   参数:{}   执行结果:{}   总耗时:{}",className,methodName, JSONUtil.toJsonStr(paramterMap),JSONUtil.toJsonStr(result),consumTime);
        return result;
    }




}
