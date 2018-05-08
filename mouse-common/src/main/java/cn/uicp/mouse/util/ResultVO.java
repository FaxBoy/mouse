package cn.uicp.mouse.util;

import java.io.Serializable;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
* @ClassName: ResultVO 
* @Description: TODO(返回格式) 
* @author shil
* @date 2017年10月19日 下午4:01:02 
* 
* @param <T>
 */
public class ResultVO<T> implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

	private static final int SUCCESS_CODE = 200;

	private Integer code;
	private String message;
	private T data;

	public ResultVO() {
	};

	public ResultVO(Integer code, String message, T result) {
		this.message = message;
		this.code = code;
		this.data = result;
	};

	public static ResultVO<?> success(String message) {
		return new ResultVO<String>(SUCCESS_CODE, message, null);
	}

	public static ResultVO<?> success(String message, Object result) {
		return new ResultVO<>(SUCCESS_CODE, message, result);
	}

	public static ResultVO<?> fail(Integer code, String message) {
		return new ResultVO<String>(code, message, null);
	}

	public static ResultVO<?> fail(Integer code, String message, Object result) {
		return new ResultVO<>(code, message, result);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
