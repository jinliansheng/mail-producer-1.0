package com.bhz.mail.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * : 动态获取主从状态 ,实现主从切换
 * : 动态代理模式
 * 
 * @author jinliansheng
 *
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataBaseContextHolder.getDataBaseType();
	}

}
