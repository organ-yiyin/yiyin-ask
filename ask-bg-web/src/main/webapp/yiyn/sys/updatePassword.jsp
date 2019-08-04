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
	
	<form method="post" action="<%=path %>/sys/user/updatePassword.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt>原密码：</dt>
				<dd><input class="required" name="password" type="password" size="30" minlength="6" maxlength="20"/></dd>
			</dl>
			<dl class="nowrap">
				<dt>新密码：</dt>
				<dd><input class="required" id="newPassword" name="newPassword" type="password" size="30" minlength="6" maxlength="20"/></dd>
			</dl>
			<dl class="nowrap">
				<dt>确认密码：</dt>
				<dd><input class="required" name="newPassword_confirm" type="password" size="30" minlength="6" maxlength="20" equalto="#newPassword"/></dd>
			</dl>
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