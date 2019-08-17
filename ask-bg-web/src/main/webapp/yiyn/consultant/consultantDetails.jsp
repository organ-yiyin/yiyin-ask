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
					<input type="text" name="user_no" class="required" value="${info.user_no}" />
				</dd>
			</dl>
			<dl>
				<dt>用户默认密码：</dt>
				<dd><input class="required" name="original_password" id="original_password" type="text" size="30" maxlength="20" readonly value="123456" /></dd>
			</dl>
			<dl class="">
				<dt>用户姓名：</dt>
				<dd>
					<input type="text" name="user_name" class="required" value="${info.user_name}"/>
				</dd>
			</dl>
			<dl class="">
				<dt>身份证号：</dt>
				<dd>
					<input type="text" name="user_id_num" value="${info.user_id_num}" />
				</dd>
			</dl>
			<dl class="">
				<dt>用户手机：</dt>
				<dd>
					<input type="text" name="user_phone" value="${info.user_phone}"/>
				</dd>
			</dl>
			<dl>
				<dt>用户类型</dt>
				<dd>
					<select class="combox required" name="user_type" class="required">
 						<option value="">请选择</option>
						<c:forEach items="${info.userTypes}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code==info.user_type}">selected</c:if>>${item.text}</option>
						</c:forEach>
 					</select>
 				</dd>
 			</dl>
			<dl class="nowrap">
				<dt>头像：</dt>
				<dd>
					<input type="text" name="user_headimg" size="70" value="${info.user_headimg}"/>
				</dd>
			</dl>
			<dl class="">
				<dt>接单设置：</dt>
				<dd>
					<select name="order_set" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.orderSets}" var="p">
							<option <c:if test="${p.code eq info.order_set}">selected</c:if> value="${p.code}">${p.text}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl class="">
				<dt>从业年限：</dt>
				<dd>
					<input type="text" name="work_year" class="digits" value="${info.work_year}"/>
				</dd>
			</dl>
			
			<dl class="">
				<dt>咨询类型：</dt>
				<dd>
					<select name="advice_type" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.consultingTypes}" var="p">
							<option <c:if test="${p.code eq info.advice_type}">selected</c:if> value="${p.code}">${p.text}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl class="">
				<dt>咨询单价：</dt>
				<dd>
					<input type="text" name="advice_val" class="number" value="${info.advice_val}"/>
				</dd>
			</dl>
			
			<dl class="">
				<dt>咨询限量：</dt>
				<dd>
					<input type="text" name="advice_num" class="digits" value="${info.advice_num}"/>
				</dd>
			</dl>
			
			<dl class="">
				<dt>是否推荐：</dt>
				<dd>
					<select name="recommend" class="combox">
						<option value="">请选择</option>
						<c:forEach items="${info.yesOrNoTypes}" var="p">
							<option <c:if test="${p.code eq info.recommend}">selected</c:if> value="${p.code}">${p.text}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl class="">
				<dt>是否隐藏：</dt>
				<dd>
					<select name="is_hidden" class="combox">
						<option value="">请选择</option>
						<c:forEach items="${info.yesOrNoTypes}" var="p">
							<option <c:if test="${p.code eq info.is_hidden}">selected</c:if> value="${p.code}">${p.text}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl class="nowrap">
				<dt>二维码分享链接：</dt>
				<dd>
					<input type="text" name="share_link" size="70" value="${info.share_link}"/>
				</dd>
			</dl>
			
			<dl class="">
				<dt>用户简介：</dt>
				<dd>
					<textarea name="user_desc" cols="80" rows="8">${info.user_desc}</textarea>
				</dd>
			</dl>
			
		</div>
		
		<div class="formBar">
			<ul>
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