<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
	var downloadExcel = function(){
		alertMsg.confirm("确认导出咨询师数据吗？", {
			okCall: function(){
				var url = "<%=path %>/consultant/downloadConsults.do";
				url = url + "?user_no=" + $("#user_no").val();
				url = url + "&user_name=" + $("#user_name").val();
				url = url + "&advice_type=" + $("#advice_type").val();
				
				window.location.href=url;
			}
		});
	};
	
</script>

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
					<input type="text" name="user_no" id="user_no" value="${info.paramMap['user_no']}" />
				</td>
				<td>
					用户姓名：
				</td>
				<td>
					<input type="text" name="user_name" id="user_name" value="${info.paramMap['user_name']}" />
				</td>
				<td>
					咨询类型：
				</td>
				<td>
					<select name="advice_type" id="advice_type" class="combox">
						<option value="">请选择</option>
						<c:forEach items="${info.consultingTypes}" var="p">
							<option <c:if test="${p.code eq info.paramMap['advice_type']}">selected</c:if> value="${p.code}">${p.name}</option>	
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
			<li><a class="edit" href="<%=path %>/consultant/forwardUpdateDetails.do?id={id}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li>
				<a class="edit" href="<%=path%>/consultant/forwardResetPass.do?id={id}" target="navTab"><span>重置密码</span></a>
			</li>
			<li class="line">line</li>
			<li><a class="edit" href="<%=path %>/consultant/attachment/management.do?id={id}" target="navTab" rel="attachmentManagement"><span>附件管理</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:void(0)" onclick="downloadExcel()"><span>导出</span></a></li>
			</ul>
	</div>

	<table class="table" style="width:720px" layoutH="140">
		<thead>
			<tr>
				<th width="150px">用户名</th>
				<th width="150px">用户姓名</th>
				<th width="120px">用户手机</th>
				<th width="100px">咨询类型</th>
				<th width="100px">接单设置</th>
				<th width="100px">从业年限</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${info.data}" var="item" varStatus="s">
			<tr target="id" rel="${item.id}">
				<td>${item.user_no}</td>
				<td>${item.user_name}</td>
				<td>${item.user_phone}</td>
				<td>
					<c:forEach items="${info.consultingTypes}" var="item_t" varStatus="s">
						<c:if test="${item_t.code==item.advice_type}">${item_t.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${info.orderSets}" var="item_t" varStatus="s">
						<c:if test="${item_t.code==item.order_set}">${item_t.name}</c:if>
					</c:forEach>
				</td>
				<td>${item.work_year}</td>
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
    
