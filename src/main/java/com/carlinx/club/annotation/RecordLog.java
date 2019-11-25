package com.carlinx.club.annotation;

import java.lang.annotation.*;

/**
 * @author yj
 * @date: 2019/11/25 0025  10:08
 */


@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordLog {
}
