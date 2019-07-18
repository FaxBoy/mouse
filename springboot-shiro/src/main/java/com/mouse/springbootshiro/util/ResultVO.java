package com.mouse.springbootshiro.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 
* @ClassName: ResultVO 
* @Description: TODO(返回格式) 
* @author shil
* @date 2017年10月19日 下午4:01:02 
* 
* @param <T>
 */
@ApiModel(value="ResultVO对象", description="")
public class ResultVO<T> implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

	private static final int SUCCESS_CODE = 200;

	@ApiModelProperty(value = "状态码")
	private Integer code;

	@ApiModelProperty(value = "提示信息")
	private String message;

	@ApiModelProperty(value = "内容body")
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
