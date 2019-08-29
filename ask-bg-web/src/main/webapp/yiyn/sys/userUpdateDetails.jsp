<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
	
</script>

<div class="pageContent">
	
	<form method="post" action="<%=path %>/sys/user/update.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="id" id="id" value="${info.id}" >
		
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt>用户名：</dt>
				<dd><input class="required" name="user_no" type="text" size="30" maxlength="20" value="${info.user_no}" readonly/></dd>
			</dl>
			<dl class="nowrap">
				<dt>用户姓名：</dt>
				<dd><input class="required" name="user_name" type="text" size="30" maxlength="20" value="${info.user_name}" /></dd>
			</dl>
			
			<dl class="nowrap">
				<dt>是否可用：</dt>
				<dd>
					<select class="combox required" name="enabled" class="required">
						<option value="">请选择</option>
						<c:forEach items="${info.yesOrNoTypes}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code==info.enabled}">selected</c:if>>${item.text}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<div class="divider"></div>
			<dl>
				<dt>创建人员：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="${info.created_by}" />
				</dd>
			</dl>
			<dl>
				<dt>创建时间：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="<fmt:formatDate value="${info.created_time}" pattern="yyyy-MM-dd HH:mm:ss" />" />
				</dd>
			</dl>
			<dl>
				<dt>最新修改人员：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="${info.updated_by}" />
				</dd>
			</dl>
			<dl>
				<dt>最新修改时间：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="<fmt:formatDate value="${info.updated_time}" pattern="yyyy-MM-dd HH:mm:ss" />" />
				</dd>
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