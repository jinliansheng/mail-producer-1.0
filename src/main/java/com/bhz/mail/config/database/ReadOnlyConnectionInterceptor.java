package com.bhz.mail.config.database;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect // 控制自定义注解
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

	public static final Logger LOGGER = Logger.getLogger(ReadOnlyConnectionInterceptor.class);

	@Around("@annotation(readOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection)
			throws Throwable {
		try {
			LOGGER.info("--------------set database connection 2 read only----------------");
			// 在使用注解之前,强制切换成从节点
			DataBaseContextHolder.setDataBaseType(DataBaseContextHolder.DataBaseType.SLAVE);
			// proceed()让注解的方法执行完毕
			Object result = proceedingJoinPoint.proceed();
			return result;
		} finally {
			DataBaseContextHolder.clearDataBaseType();
			LOGGER.info("--------------clear database connection----------------");
		}
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
