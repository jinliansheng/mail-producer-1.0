package com.bhz.mail.config.database;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author jinliansheng
 *
 * @param <T>通用的增删改查方法
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T>{

}
