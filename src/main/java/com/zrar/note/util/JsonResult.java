package com.zrar.note.util;
/**
 * 用于封装AJAX调用以后的JSON返回值
 * 其中正确返回值:
 *  {state:0, data:返回数据, message:""}
 * 错误返回值:
 *  {state:1, data:null, message:错误消息}
 */
import java.io.Serializable;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 返回是否成功的状态：0 成功；1 失败
	 */
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	//成功时返回data
	private Object data;
	//失败时返回message
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
