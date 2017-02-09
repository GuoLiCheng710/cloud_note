package com.zrar.note.util;
/**
 * ���ڷ�װAJAX�����Ժ��JSON����ֵ
 * ������ȷ����ֵ:
 *  {state:0, data:��������, message:""}
 * ���󷵻�ֵ:
 *  {state:1, data:null, message:������Ϣ}
 */
import java.io.Serializable;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * �����Ƿ�ɹ���״̬��0 �ɹ���1 ʧ��
	 */
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	//�ɹ�ʱ����data
	private Object data;
	//ʧ��ʱ����message
	private String message;
	
	public JsonResult() {
		
	}
	
	public JsonResult(int state, Object data, String message) {
		super();
		this.state = state;
		this.data = data;
		this.message = message;
	}

	public JsonResult(Throwable e){
		state = ERROR;
		data = null;
		message = e.getMessage();
		
	}
	public JsonResult(Object data){
		state = SUCCESS;
		this.data = data;
		message = "";
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + "]";
	}
	
	

}
