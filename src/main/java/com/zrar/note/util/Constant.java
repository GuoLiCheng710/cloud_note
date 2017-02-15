package com.zrar.note.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	/**�ʼǱ�����*/
	public static final String NOTEBOOK_TYPE_ID_1 = "1";
	public static final String NOTEBOOK_TYPE_ID_2 = "2";
	public static final String NOTEBOOK_TYPE_ID_3 = "3";
	public static final String NOTEBOOK_TYPE_ID_4 = "4";
	public static final String NOTEBOOK_TYPE_ID_5 = "5";
	
	public static final String NOTEBOOK_TYPE_NAME_1 = "�ղ�";
	public static final String NOTEBOOK_TYPE_NAME_2 = "����վ";
	public static final String NOTEBOOK_TYPE_NAME_3 = "�";
	public static final String NOTEBOOK_TYPE_NAME_4 = "����";
	public static final String NOTEBOOK_TYPE_NAME_5 = "����";
	
	public static final Map<String, String> NOTEBOOK_TYPE_MAP = new HashMap<String, String>();
	static{
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_1, NOTEBOOK_TYPE_NAME_1);
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_2, NOTEBOOK_TYPE_NAME_2);
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_3, NOTEBOOK_TYPE_NAME_3);
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_4, NOTEBOOK_TYPE_NAME_4);
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_5, NOTEBOOK_TYPE_NAME_5);
	}
	
}
