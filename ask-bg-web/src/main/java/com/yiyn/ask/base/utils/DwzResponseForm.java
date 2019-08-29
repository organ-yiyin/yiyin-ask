package com.yiyn.ask.base.utils;

import javax.servlet.http.HttpServletRequest;

public class DwzResponseForm {
	
	private String statusCode;
	
	private String message;
	
	private String navTabId;
	
	private String rel;
	
	private String callbackType;
	
	private String forwardUrl;
	
	private String confirmMsg;
	
	private Object data;
	
	public DwzResponseForm(){
	}
	
	public static DwzResponseForm createFailResponseForm(String message){
		return new DwzResponseForm(
				StatusCode.FAIL.getValue(), message,
				"", "", "", "", "");
	}
	
	public static DwzResponseForm createSuccessResponseForm(){
		return new DwzResponseForm(
				StatusCode.SUCCESS.getValue(), StatusCode.SUCCESS.getMessage(),
				"", "", "", "", "");
	}
	
	public static DwzResponseForm createSuccessResponseForm(String message){
		return new DwzResponseForm(
				StatusCode.SUCCESS.getValue(), message,
				"", "", "", "", "");
	}
	
	public static DwzResponseForm createForwardResponseForm(HttpServletRequest request, String forwardUrl){
		return new DwzResponseForm(
				StatusCode.SUCCESS.getValue(), StatusCode.SUCCESS.getMessage(),
				"", "", CallBackType.Forward.getValue(), request.getContextPath() + forwardUrl, "");
	}
	
	public static DwzResponseForm createCloseCurrentResponseForm(){
		return new DwzResponseForm(
				StatusCode.SUCCESS.getValue(), StatusCode.SUCCESS.getMessage(),
				"", "", CallBackType.CloseCurrent.getValue(), "", "");
	}
	
	public DwzResponseForm(String statusCode, String message, String navTabId,
			String rel, String callbackType, String forwardUrl,
			String confirmMsg) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.rel = rel;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
		this.confirmMsg = confirmMsg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
	
	public enum StatusCode{
		
		//操作成功,操作失败,会话超时
		SUCCESS("200","操作成功"),FAIL("300","操作失败"),TIME_OUT("301","会话超时");
		
		String value;
		
		private String message;
		
		private StatusCode(String value, String message){
			this.value = value;
			this.message = message;
		}

		public String getValue() {
			return value;
		}

		public String getMessage() {
			return message;
		}
	}
	
	public enum CallBackType{
		
		CloseCurrent("closeCurrent"),Forward("forward");
		
		String value;
		
		private CallBackType(String value){
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
