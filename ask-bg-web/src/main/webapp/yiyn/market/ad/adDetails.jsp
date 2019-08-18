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

	<form method="post" action="<%=path%>/market/ad/save.do" class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">

		<input type="hidden" name="id" id="id" value="${info.id}">
		
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt>广告标题：</dt>
				<dd>
					<input class="required" maxlength="200" name="ad_title" type="text" size="60" value="${info.ad_title}" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>广告位置：</dt>
				<dd>
					<select class="combox required" name="ad_position">
						<option value="">请选择</option>
						<c:forEach items="${info.adPositions}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code eq info.ad_position}">selected</c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>广告链接：</dt>
				<dd>
					<input maxlength="200" name="ad_url" type="text" size="60" value="${info.ad_url}" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>图片链接：</dt>
				<dd>
					<input maxlength="200" name="pic_url" type="text" size="60" value="${info.pic_url}" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>广告备注：</dt>
				<dd>
					<textarea class="editor" maxlength="500" name="description" rows="18" cols="70" tools="mfull">${info.description}</textarea>
				</dd>
			</dl>

			<dl class="nowrap">
				<dt>是否禁用：</dt>
				<dd>
					<select class="combox required" name="delete_flag">
						<option value="">请选择</option>
						<c:forEach items="${info.yesOrNoTypes}" var="item" varStatus="s">
							<option value="${item.code}"
								<c:if test="${item.code==info.delete_flag}">selected</c:if>>${item.text}</option>
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