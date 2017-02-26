package com.zrar.note.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
	private static final String salt = "tedu.cn是一家培训机构";
	public static String salMD5(String data){
		return DigestUtils.md5Hex(data+salt);
	}
	
}
