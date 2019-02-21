package com.bhz.mail.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * @author jinliansheng
 * 
 *         主键生成策略
 */
public class KeyUtil {

	public static String generatorUUID() {
		TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
		return timeBasedGenerator.generate().toString();
	}

	public static void main(String[] args) {
		System.out.println("主键+——+——+——+——" + KeyUtil.generatorUUID());
	}
}
