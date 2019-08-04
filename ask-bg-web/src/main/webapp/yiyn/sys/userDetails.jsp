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
	
	<form method="post" action="<%=path %>/sys/user/save.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="id" id="id" value="${info.id}" >
		
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>用户名：</dt>
				<dd><input class="required" name="user_no" type="text" size="30" maxlength="20" value="${info.user_no}" /></dd>
			</dl>
			<dl>
				<dt>用户姓名：</dt>
				<dd><input class="required" name="user_name" type="text" size="30" maxlength="20" value="${info.user_name}" /></dd>
			</dl>
			<dl class="nowrap">
				<dt>用户类型：</dt>
				<dd>
					<select class="combox required" name="user_type" class="required">
						<option value="">请选择</option>
						<c:forEach items="${info.userTypes}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code==info.user_type}">selected</c:if>>${item.text}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>

			<c:if test="${empty info.id}">
			<dl>
				<dt>新增用户默认密码：</dt>
				<dd><input class="required" name="original_password" id="original_password" type="text" size="30" maxlength="20" readonly value="123456" /></dd>
			</dl>
			</c:if>

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