<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageHeader">

	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="<%=path %>/consultant/search.do" method="post">
	<input type="hidden" name="resourceStatus" value=""/>
	<input type="hidden" name="pageNum" value="${info.currentPage}" />
	<input type="hidden" name="numPerPage" value="${info.numPerPage}" /><!--【可选】每页显示多少条-->
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：
				</td>
				<td>
					<input type="text" name="user_no" value="${info.paramMap['user_no']}" />
				</td>
				<td>
					用户姓名：
				</td>
				<td>
					<input type="text" name="user_name" value="${info.paramMap['user_name']}" />
				</td>
				<td>
					用户类型：
				</td>
				<td>
					<select class="combox" name="user_type">
						<option value="">请选择</option>
						<c:forEach items="${info.userTypes}" var="item_u" varStatus="s">
							<option value="${item_u.code}" <c:if test="${item_u.code==info.paramMap['user_type']}">selected</c:if>>${item_u.text}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
		</table>
		<div class="subBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div>
				</li>
			</ul>
		</div>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=path %>/consultant/forwardNewDetails.do" target="navTab"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="<%=path %>/consultant/forwardUpdateDetails.do?id={id}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li>
				<a class="edit" href="<%=path%>/consultant/forwardResetPass.do?id={id}" target="navTab"><span>重置密码</span></a>
			</li>
			</ul>
	</div>

	<table class="table" style="width:500px" layoutH="140">
		<thead>
			<tr>
				<th width="200px">用户名</th>
				<th width="200px">用户姓名</th>
				<th width="100px">用户类型</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${info.data}" var="item" varStatus="s">
			<tr target="id" rel="${item.id}">
				<td>${item.user_no}</td>
				<td>${item.user_name}</td>
				<td>
					<c:forEach items="${info.userTypes}" var="item_u" varStatus="s">
						<c:if test="${item_u.code==item.user_type}">${item_u.text}</c:if>
					</c:forEach>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10" <c:if test="${info.numPerPage==10}">selected</c:if>>10</option>
				<option value="20" <c:if test="${info.numPerPage==20}">selected</c:if>>20</option>
				<option value="50" <c:if test="${info.numPerPage==50}">selected</c:if>>50</option>
			</select>
			<span>条，共${info.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${info.totalCount}" numPerPage="${info.numPerPage}" pageNumShown="5" currentPage="${info.currentPage}"></div>
	</div>
	
</div>
    
