<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post" action="<%=path %>/consultant/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" id="id" value="${info.id}"/>
		
		<div class="pageFormContent" layoutH="56">
			<dl class="">
				<dt>用户名：</dt>
				<dd>
					${info.user_no}
				</dd>
			</dl>
			<dl class="">
				<dt>用户姓名：</dt>
				<dd>
					${info.user_name}
				</dd>
			</dl>
			<div class="divider"></div>
			
			<dl class="nowrap">
				<dt>余额：</dt>
				<dd>
					${accountPo.balance}
				</dd>
			</dl>
			<dl class="">
				<dt>已提现金额：</dt>
				<dd>
					${accountPo.withdraw}
				</dd>
			</dl>
			<dl class="">
				<dt>可提现金额：</dt>
				<dd>
					${accountPo.withdraw_enable}
				</dd>
			</dl>
			
			<div class="divider"></div>
			<dl>
				<dt>创建人员：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="${accountPo.created_by}" />
				</dd>
			</dl>
			<dl>
				<dt>创建时间：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="<fmt:formatDate value="${accountPo.created_time}" pattern="yyyy-MM-dd HH:mm:ss" />" />
				</dd>
			</dl>
			<dl>
				<dt>最新修改人员：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="${accountPo.updated_by}" />
				</dd>
			</dl>
			<dl>
				<dt>最新修改时间：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="<fmt:formatDate value="${accountPo.updated_time}" pattern="yyyy-MM-dd HH:mm:ss" />" />
				</dd>
			</dl>
			
		</div>
		
		<div class="formBar">
			<ul>
				
			</ul>
		</div>
	</form>
</div>