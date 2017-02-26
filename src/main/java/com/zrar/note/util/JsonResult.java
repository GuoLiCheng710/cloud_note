package com.zrar.note.util;
/**
 * 包装返回值
 * 成功时返回：
 *  {state:0, data:返回内容, message:""}
 * 异常是返回：
 *  {state:1, data:null, message:异常信息}
 */
import java.io.Serializable;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 常量
	 */
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	//成功时返回data
	private Object data;
	//异常时返回message
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
	public JsonResult (int state ,Throwable e){
		this.state = state;
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
