package com.bhz.mail.config.database;

/**
 * @author jinliansheng :主从切换设置
 */
public class DataBaseContextHolder {
	public enum DataBaseType {
		MASTER, SLAVE
	}

	private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<DataBaseType>();

	/**
	 * @param dataBaseType
	 */
	public static void setDataBaseType(DataBaseType dataBaseType){
		if (dataBaseType == null) {
		contextHolder.set(dataBaseType);
		}
		throw new NullPointerException() ;
	}

	/**
	 * @return
	 */
	public static DataBaseType getDataBaseType() {
		return contextHolder.get() == null ? DataBaseType.MASTER : contextHolder.get();
	}

	/**
	 * 清空线程
	 */
	public static void clearDataBaseType() {
		contextHolder.remove();
	}
}
