package com.zrar.note.util;

import java.lang.Thread.State;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
	private static final String salt = "tedu.cn��һ����ѵ����";
	public static String salMD5(String data){
		return DigestUtils.md5Hex(data+salt);
	}
	
}