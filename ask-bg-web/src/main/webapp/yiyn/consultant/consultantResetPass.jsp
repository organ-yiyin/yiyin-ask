<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
	
</script>

<div class="pageContent">
	
	<form method="post" action="<%=path %>/consultant/resetPassword.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="id" id="id" value="${info.id}" >
		
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt>用户名：</dt>
				<dd><input class="required" name="user_name" type="text" size="30" maxlength="20" value="${info.user_no}" readonly /></dd>
			</dl>
			<dl class="nowrap">
				<dt>用户姓名：</dt>
				<dd><input class="required" name="full_name" type="text" size="30" maxlength="20" value="${info.user_name}" readonly /></dd>
			</dl>
			<dl class="nowrap">
				<dt>重置用户密码：</dt>
				<dd><input class="required" name="original_password" type="text" size="30" minlength="6" maxlength="20" value="" /></dd>
			</dl>
			<div class="divider"></div>
			
		</div>
		
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>