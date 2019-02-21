package com.bhz.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author jinliansheng
 *
 */
@EnableWebMvc // 启用spring mvc
@Configuration // 让spring boot项目启动时识别当前xml配置类 
@ComponentScan("com.bhz.mail.*")
@MapperScan(basePackages = "com.bhz.mail.mapper")
public class Mainconfig {
}
