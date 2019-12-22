<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script>
	
</script>

<div class="pageContent">

	<form method="post" action="<%=path%>/market/coupon/save.do" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">

		<input type="hidden" name="id" id="id" value="${info.id}">
		
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt>活动名称：</dt>
				<dd>
					<input class="required" maxlength="200" name="name" type="text" size="60" value="${info.name}" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>活动编号：</dt>
				<dd>
					<input class="required" maxlength="200" name="coupon_no" type="text" value="${info.coupon_no}" />
				</dd>
			</dl>
			
			<dl class="">
				<dt>类型：</dt>
				<dd>
					<select class="combox required" name="coupon_type">
						<option value="">请选择</option>
						<c:forEach items="${info.couponTypeList}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code eq info.coupon_type}">selected</c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="">
				<dt>范围：</dt>
				<dd>
					<select class="combox required" name="coupon_range">
						<option value="">请选择</option>
						<c:forEach items="${info.couponRangeList}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code eq info.coupon_range}">selected</c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>状态：</dt>
				<dd>
					<select class="combox required" name="status">
						<option value="">请选择</option>
						<c:forEach items="${info.couponStatusList}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code eq info.status}">selected</c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="">
				<dt>满减金额(满)：</dt>
				<dd>
					<input class="required digits" maxlength="6" name="total_amount" type="text" value="${info.total_amount}" />
				</dd>
			</dl>
			<dl class="">
				<dt>满减金额(减)：</dt>
				<dd>
					<input class="required digits" maxlength="6" name="amount" type="text" value="${info.amount}" />
				</dd>
			</dl>
			
			<dl class="">
				<dt>活动开始时间：</dt>
				<dd>
					<input class="date" maxlength="200" name="start_date" type="text" value="${info.start_date}" />
				</dd>
			</dl>
			<dl class="">
				<dt>活动结束时间：</dt>
				<dd>
					<input class="date" maxlength="200" name="end_date" type="text" value="${info.end_date}" />
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