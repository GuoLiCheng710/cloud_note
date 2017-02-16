package com.zrar.note.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	/**�ʼ�����*/
	public static final String NOTE_TYPE_ID_0 = "0";
	public static final String NOTE_TYPE_ID_1 = "1";
	public static final String NOTE_TYPE_ID_2 = "2";
	public static final String NOTE_TYPE_ID_3 = "3";
	public static final String NOTE_TYPE_ID_4 = "4";
	public static final String NOTE_TYPE_ID_5 = "5";
	
	public static final String NOTE_TYPE_NAME_0 = "ɾ��";
	public static final String NOTE_TYPE_NAME_1 = "�ղ�";
	public static final String NOTE_TYPE_NAME_2 = "����վ";
	public static final String NOTE_TYPE_NAME_3 = "�";
	public static final String NOTE_TYPE_NAME_4 = "����";
	public static final String NOTE_TYPE_NAME_5 = "����";
	
	public static final Map<String, String> NOTE_TYPE_MAP = new HashMap<String, String>();
	static{
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_0, NOTE_TYPE_NAME_0);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_1, NOTE_TYPE_NAME_1);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_2, NOTE_TYPE_NAME_2);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_3, NOTE_TYPE_NAME_3);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_4, NOTE_TYPE_NAME_4);
		NOTE_TYPE_MAP.put(NOTE_TYPE_ID_5, NOTE_TYPE_NAME_5);
	}
	/**�ʼ����� END*/
	
	/**�ʼǱ�����*/
	public static final String NOTEBOOK_TYPE_ID_0 = "0";
	public static final String NOTEBOOK_TYPE_ID_1 = "1";
	
	public static final String NOTEBOOK_TYPE_NAME_0 = "ɾ��";
	public static final String NOTEBOOK_TYPE_NAME_1 = "����";
	
	public static final Map<String, String> NOTEBOOK_TYPE_MAP = new HashMap<String, String>();
	static {
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_0, NOTEBOOK_TYPE_NAME_0);
		NOTEBOOK_TYPE_MAP.put(NOTEBOOK_TYPE_ID_1, NOTEBOOK_TYPE_NAME_1);
	}
	/**�ʼǱ�����END*/
}
