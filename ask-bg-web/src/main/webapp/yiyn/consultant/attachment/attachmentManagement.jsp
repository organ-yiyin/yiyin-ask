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
		<input type="hidden" name="id" id="id" value="${userForm.id}"/>
		
		<div class="pageFormContent">
			<dl class="">
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="user_no" class="required" value="${userForm.user_no}" readonly/>
				</dd>
			</dl>
			<dl class="">
				<dt>用户姓名：</dt>
				<dd>
					<input type="text" name="user_name" class="required" value="${userForm.user_name}" readonly/>
				</dd>
			</dl>
		</div>
		<br>
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
					<li><a class="add" href="<%=path %>/consultant/attachment/forwardNewDetails.do?object_id=${userForm.id}" target="dialog" rel="newAttachment" mask="true"  width="900" height="400"><span>新增</span></a></li>
					<li class="line">line</li>
					<li><a class="delete" href="<%=path %>/consultant/attachment/delete.do?id={attachment_id}" target="ajaxTodo" title="确定要删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
				</ul>
			</div>
			
			<table class="table" style="width:750px" layoutH="140">
				<thead>
					<tr>
						<th width="200px">附件名称</th>
						<th width="150px">附件类型</th>
						<th width="400px">附件地址</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${info.data}" var="item" varStatus="s">
					<tr target="attachment_id" rel="${item.id}">
						<td>${item.attachment_name}</td>
						<td>
							<c:forEach items="${info.attachmentTypes}" var="item_u" varStatus="s">
								<c:if test="${item_u.code==item.attachment_type}">${item_u.name}</c:if>
							</c:forEach>
						</td>
						<td>${item.oss_url}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</form>
</div>