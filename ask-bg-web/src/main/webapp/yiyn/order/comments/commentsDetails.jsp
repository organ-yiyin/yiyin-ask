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
	
	<form method="post" action="<%=path %>/comments/save.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="id" id="id" value="${info.id}" >
		
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt>订单号：</dt>
				<dd>${consultantSheet.odd_num}</dd>
			</dl>
			<dl class="">
				<dt>下单帐号：</dt>
				<dd>${userC.user_no}</dd>
			</dl>
			<dl class="">
				<dt>下单手机号：</dt>
				<dd>${userC.user_phone}</dd>
			</dl>
			<dl class="">
				<dt>咨询师姓名：</dt>
				<dd>${userB.user_name}</dd>
			</dl>
			<dl class="">
				<dt>咨询师电话：</dt>
				<dd>${userB.user_phone}</dd>
			</dl>
			
			<div class="divider"></div>
			<dl class="">
				<dt>星级：</dt>
				<dd>
					<select name="stars" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.commentsStars}" var="p">
							<option <c:if test="${p.code eq userEval.stars}">selected</c:if> value="${p.code}">${p.name}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>评论：</dt>
				<dd><textarea name="eva_desc" id="eva_desc" cols="80" rows="8">${userEval.eva_desc}</textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt>是否隐藏：</dt>
				<dd>
					<select name="is_hidden" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.yesOrNoTypes}" var="p">
							<option <c:if test="${p.code eq userEval.is_hidden}">selected</c:if> value="${p.code}">${p.text}</option>	
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