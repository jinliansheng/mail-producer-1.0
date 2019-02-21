package com.bhz.mail.config.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jinliansheng 
 * : @interface 是用来修饰 Annotation （注解）的，
 */
@Target({ ElementType.METHOD, ElementType.TYPE }) // 注解只能在ElementType设定的范围内使用，否则将会编译报错
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnlyConnection {

}
