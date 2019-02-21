package com.bhz.mail.config.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@EnableTransactionManagement // 开启事务
public class DataSourceConfiguration {
	private static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

	@Value("${druid.type}")
	private Class<? extends DataSource> dataSourceType;

	@Bean(name = "masterDataSource")
	@Primary // 加此注解会优先选择此连接池
	@ConfigurationProperties(prefix = "druid.master")
	public DataSource masterDataSource() throws SQLException {
		DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
		LOGGER.info("==========MASTER:{}=========", masterDataSource);
		return masterDataSource;
	} 

	@Bean(name = "slaveDataSource")
	@ConfigurationProperties(prefix = "druid.slave")
	public DataSource slaveDataSource() throws SQLException {
		DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
		LOGGER.info("==========SLAVE:{}=========", masterDataSource);
		return masterDataSource;
	}

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("allow", "localhost");
		reg.addInitParameter("deny", "/deny");
		LOGGER.info("druid console manager init:{}", reg);
		return reg;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,*.png,*.css,*.ico,/druid/*");
		LOGGER.info("druid filter register :{}", filterRegistrationBean);
		return filterRegistrationBean;

	}

}