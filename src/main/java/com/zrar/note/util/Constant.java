package com.zrar.note.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	/**笔记类型*/
	public static final String NOTE_TYPE_ID_0 = "0";
	public static final String NOTE_TYPE_ID_1 = "1";
	public static final String NOTE_TYPE_ID_2 = "2";
	public static final String NOTE_TYPE_ID_3 = "3";
	public static final String NOTE_TYPE_ID_4 = "4";
	public static final String NOTE_TYPE_ID_5 = "5";
	
	public static final String NOTE_TYPE_NAME_0 = "删除";
	public static final String NOTE_TYPE_NAME_1 = "收藏";
	public static final String NOTE_TYPE_NAME_2 = "回收站";
	public static final String NOTE_TYPE_NAME_3 = "活动";
	public static final String NOTE_TYPE_NAME_4 = "推送";
	public static final String NOTE_TYPE_NAME_5 = "正常";
	
	public static final Map<String, String> NOTE_TYPE_MAP = new HashMap<String, String>();
	static{
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_0, NOTE_TYPE_NAME_0);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_1, NOTE_TYPE_NAME_1);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_2, NOTE_TYPE_NAME_2);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_3, NOTE_TYPE_NAME_3);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_4, NOTE_TYPE_NAME_4);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_5, NOTE_TYPE_NAME_5);
	}
	/**笔记类型 END*/
	
	/**笔记本类型*/
	public static final String NOTEBOOK_TYPE_ID_0 = "0";
	public static final String NOTEBOOK_TYPE_ID_1 = "1";
	
	public static final String NOTEBOOK_TYPE_NAME_0 = "删除";
	public static final String NOTEBOOK_TYPE_NAME_1 = "正常";
	
	public static final Map<String, String> NOTEBOOK_TYPE_MAP = new HashMap<String, String>();
	static {
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_0, NOTEBOOK_TYPE_NAME_0);
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_1, NOTEBOOK_TYPE_NAME_1);
	}
	/**笔记本类型END*/
}
