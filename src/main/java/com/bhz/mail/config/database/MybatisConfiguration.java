package com.bhz.mail.config.database;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.aspectj.apache.bcel.util.ClassLoaderRepository.SoftHashMap;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author jinliansheng
 *
 */
@Configuration // 相当于配置的xml
@AutoConfigureAfter({ DataSourceConfiguration.class }) // 表示先加载DataSourceConfiguration再执行本类，有顺序关系
public class MybatisConfiguration extends MybatisAutoConfiguration {

	// 注入主从数据源

	@Resource(name = "masterDataSource")
	private DataSource masterDataSource;

	@Resource(name = "slaveDataSource")
	private DataSource slaveDataSource;

	/**
	 * @return
	 * @throws Exception
	 * :SqlSessionFactory管理数据源
	 */
	public SqlSessionFactory sqlSessionFactory() throws Exception {

		return super.sqlSessionFactory(roundRoutingDataSourceProy());
	}

	/**
	 * @return : 获取当前数据源实现主从切换
	 */
	public AbstractRoutingDataSource roundRoutingDataSourceProy() {
		//调用实现主从切换的类(动态代理)
		ReadWriteSplitRoutingDataSource proxy=new ReadWriteSplitRoutingDataSource();
		
		SoftHashMap  targetDataSource=new ClassLoaderRepository.SoftHashMap();
		//注入数据源
		targetDataSource.put(DataBaseContextHolder.DataBaseType.MASTER, masterDataSource);
		targetDataSource.put(DataBaseContextHolder.DataBaseType.SLAVE, slaveDataSource);
		
		//默认使用主数据源
		proxy.setDefaultTargetDataSource(masterDataSource);
		
		//装入两个主从数据源
		proxy.setTargetDataSources(targetDataSource);
		
		return proxy;
	}

}
