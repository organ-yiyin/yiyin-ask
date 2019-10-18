<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="pageHeader">

	<form id="pagerForm" onsubmit="return navTabSearch(this)"
		action="<%=path%>/market/ad/search.do" method="post">
		<input type="hidden" name="resourceStatus" value="" /> <input
			type="hidden" name="pageNum" value="${info.currentPage}" /> <input
			type="hidden" name="numPerPage" value="${info.numPerPage}" />
		<!--【可选】每页显示多少条-->

		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>广告标题：</td>
					<td><input type="text" name="ad_title" value="${info.paramMap['ad_title']}" /></td>
					<td>广告位置：</td>
					<td>
						<select class="combox" name="ad_position">
							<option value="">请选择</option>
							<c:forEach items="${info.adPositions}" var="item" varStatus="s">
								<option value="${item.code}"
									<c:if test="${item.code==info.paramMap['ad_position']}">selected</c:if>>${item.name}</option>
							</c:forEach>
						</select>
					</td>
					<td>是否禁用：</td>
					<td>
						<select class="combox" name="delete_flag">
							<option value="">请选择</option>
							<c:forEach items="${info.yesOrNoTypes}" var="item" varStatus="s">
								<option value="${item.code}"
									<c:if test="${item.code==info.paramMap['delete_flag']}">selected</c:if>>${item.text}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<%--
			<li><a class="add"
				href="<%=path%>/marketing/advertising/forwardNewDetails.do"
				target="navTab"><span>添加</span></a></li>
			<li><a class="delete"
				href="<%=path%>/marketing/advertising/deleteAdvertisingById.do?ad_id={ad_id}"
				target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			 --%>
			<!-- <li><a class="delete" href="<%=path%>/marketing/advertising/deleteAdvertisingByIds.do" postType="string" rel="ad_ids" target="selectedTodo" title="确定要删除吗?"><span>删除</span></a></li>-->
			<li>
				<a class="add" href="<%=path%>/market/ad/forwardNewDetails.do" target="navTab"><span>新增</span></a>
			</li>
			<li class="line">line</li>
			<li>
				<a class="edit" href="<%=path%>/market/ad/forwardUpdateDetails.do?id={id}" target="navTab"><span>修改</span></a>
			</li>
			<li class="line">line</li>
		</ul>
	</div>

	<table class="table" style="width:700px" layoutH="140">
		<thead>
			<tr>
				<th width="300px">广告标题</th>
				<th width="200px">广告位置</th>
				<th width="100px">排序序号</th>
				<th width="100px">是否禁用</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${info.data}" var="item" varStatus="s">
				<tr target="id" rel="${item.id}">
					<td>${item.ad_title}</td>
					<td>
						<c:forEach items="${info.adPositions}" var="s_item" varStatus="s">
							<c:if test="${s_item.code==item.ad_position}">${s_item.name}</c:if>
						</c:forEach>
					</td>
					<td>${item.order_num}</td>
					<td>
						<c:forEach items="${info.yesOrNoTypes}" var="s_item" varStatus="s">
							<c:if test="${s_item.code==item.delete_flag}">${s_item.text}</c:if>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"
					<c:if test="${info.numPerPage==10}">selected</c:if>>10</option>
				<option value="20"
					<c:if test="${info.numPerPage==20}">selected</c:if>>20</option>
				<option value="50"
					<c:if test="${info.numPerPage==50}">selected</c:if>>50</option>
			</select> <span>条，共${info.totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab"
			totalCount="${info.totalCount}" numPerPage="${info.numPerPage}"
			pageNumShown="5" currentPage="${info.currentPage}"></div>
	</div>

</div>