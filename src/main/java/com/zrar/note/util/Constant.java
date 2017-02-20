package com.zrar.note.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	/**笔记类型*/
	public static final String NOTE_STATUS_ID_0 = "0";
	public static final String NOTE_STATUS_ID_1 = "1";
	public static final String NOTE_STATUS_ID_2 = "2";
	public static final String NOTE_STATUS_ID_3 = "3";
	public static final String NOTE_STATUS_ID_4 = "4";
	public static final String NOTE_STATUS_ID_5 = "5";
	
	public static final String NOTE_STATUS_NAME_0 = "删除";
	public static final String NOTE_STATUS_NAME_1 = "正常";
	public static final String NOTE_STATUS_NAME_2 = "回收站";
	public static final String NOTE_STATUS_NAME_3 = "活动";
	public static final String NOTE_STATUS_NAME_4 = "推送";
	public static final String NOTE_STATUS_NAME_5 = "收藏";
	
	public static final Map<String, String> NOTE_STATUS_MAP = new HashMap<String, String>();
	static{
		NOTE_STATUS_MAP.put(NOTE_STATUS_ID_0, NOTE_STATUS_NAME_0);
		NOTE_STATUS_MAP.put(NOTE_STATUS_ID_1, NOTE_STATUS_NAME_1);
		NOTE_STATUS_MAP.put(NOTE_STATUS_ID_2, NOTE_STATUS_NAME_2);
		NOTE_STATUS_MAP.put(NOTE_STATUS_ID_3, NOTE_STATUS_NAME_3);
		NOTE_STATUS_MAP.put(NOTE_STATUS_ID_4, NOTE_STATUS_NAME_4);
		NOTE_STATUS_MAP.put(NOTE_STATUS_ID_5, NOTE_STATUS_NAME_5);
	}
	/**笔记类型 END*/
	
	/**笔记本类型*/
	public static final String NOTEBOOK_STATUS_ID_0 = "0";
	public static final String NOTEBOOK_STATUS_ID_1 = "1";
	
	public static final String NOTEBOOK_STATUS_NAME_0 = "删除";
	public static final String NOTEBOOK_STATUS_NAME_1 = "正常";
	
	public static final Map<String, String> NOTEBOOK_STATUS_MAP = new HashMap<String, String>();
	static {
		NOTEBOOK_STATUS_MAP.put(NOTEBOOK_STATUS_ID_0, NOTEBOOK_STATUS_NAME_0);
		NOTEBOOK_STATUS_MAP.put(NOTEBOOK_STATUS_ID_1, NOTEBOOK_STATUS_NAME_1);
	}
	/**笔记本类型END*/
}
