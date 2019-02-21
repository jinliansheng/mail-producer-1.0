package com.bhz.mail.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonConverUtil {

	private static final SerializerFeature[] featuresWithNullValue = { SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteNullListAsEmpty,
			SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullBooleanAsFalse };

	/**
	 * : 将JSONO字符串转换为实体对象
	 * 
	 * @param data
	 * @param clzss
	 * @return
	 */
	public static <T> T convertJSONToObject(String data, Class<T> clzss) {
		try {
			T t = JSON.parseObject(data, clzss);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * : 将JSONObject字符串转换为实体对象
	 * 
	 * @param data
	 * @param clzss
	 * @return
	 */
	public static <T> T convertJSONToObject(JSONObject data, Class<T> clzss) {
		try {
			T t = JSONObject.toJavaObject(data, clzss);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * : 将JSONO字符串转换为List集合对象
	 * 
	 * @param data
	 * @param clzss
	 * @return
	 */
	public static <T> List<T> convertJSONToArray(String data, Class<T> clzss) {
		try {
			List<T> t = JSON.parseArray(data, clzss);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * : 将对象转换为JSON字符串
	 * @param obj
	 * @return
	 */
	public static String convertObjectToJSON(Object obj) {
		try {
			String text = JSON.toJSONString(obj);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * : 将对象转换为JSONObject对象
	 * 
	 * @param obj
	 * @return
	 */
	public static JSONObject convertObjectToJSONObject(Object obj) {
		try {
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String convertObjectToJSONObjectWriteNullValue(Object obj) {
		try {
			String text = JSON.toJSONString(obj, featuresWithNullValue);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println(System.getProperties());

	}

}
