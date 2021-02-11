package com.rmi.learn.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @Author:         cong zhi
 * @CreateDate:     2021/2/7 10:51
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/2/7 10:51
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {

    /**
     * 对外发布的服务接口地址
     * @author      cong zhi
     * @date        2021/2/7 10:50
     */
    Class<?> value();

    /**
     * 版本
     */
    String version() default "";
}
