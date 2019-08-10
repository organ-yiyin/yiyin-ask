package com.yiyn.ask.wechat.dto;

public class WechatResultDto<T extends WechatBaseResponseDto> {
	
	/**
	 * 是否成功
	 * true的时候，可以获取result对象
	 * false的时候，可以获取message
	 */
	private boolean success;
	
	private String message;
	
	private T result;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
