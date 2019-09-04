<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
	var downloadExcel = function(){
		alertMsg.confirm("确认导出提现数据吗？", {
			okCall: function(){
				var url = "<%=path %>/withdraw/downloadWithdraw.do";
				url = url + "?user_no=" + $("#user_no").val();
				url = url + "&withdraw_type=" + $("#withdraw_type").val();
				url = url + "&status=" + $("#status").val();
				
				window.location.href=url;
			}
		});
	};
	
</script>

<div class="pageHeader">

	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="<%=path %>/withdraw/search.do" method="post">
	<input type="hidden" name="resourceStatus" value=""/>
	<input type="hidden" name="pageNum" value="${info.currentPage}" />
	<input type="hidden" name="numPerPage" value="${info.numPerPage}" /><!--【可选】每页显示多少条-->
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					提现账户：
				</td>
				<td>
					<input type="text" name="user_no" id="user_no" value="${info.paramMap['user_no']}" />
				</td>
				<td>
					提现类型：
				</td>
				<td>
					<select name="withdraw_type" id="withdraw_type" class="combox">
						<option value="">请选择</option>
						<c:forEach items="${info.withdrawTypes}" var="p">
							<option <c:if test="${p.code eq info.paramMap['withdraw_type']}">selected</c:if> value="${p.code}">${p.name}</option>	
						</c:forEach>
					</select>
				</td>
				<td>
					状态：
				</td>
				<td>
					<select name="status" id="status" class="combox">
						<option value="">请选择</option>
						<c:forEach items="${info.withdrawStatus}" var="p">
							<option <c:if test="${p.code eq info.paramMap['status']}">selected</c:if> value="${p.code}">${p.name}</option>	
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
			<li><a class="edit" href="<%=path %>/withdraw/forwardDetails.do?id={id}" target="navTab" rel="withdrawDetails"><span>查看</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:void(0)" onclick="downloadExcel()"><span>导出</span></a></li>
		</ul>
	</div>

	<table class="table" style="width:950px" layoutH="140">
		<thead>
			<tr>
				<th width="150px">提现账户</th>
				<th width="100px">提现金额</th>
				<th width="100px">实际到账金额</th>
				<th width="100px">提现类型</th>
				<th width="100px">状态</th>
				<th width="150px">提现申请时间</th>
				<th width="150px">操作时间</th>
				<th width="100px">操作人</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${info.data}" var="item" varStatus="s">
			<tr target="id" rel="${item['ID']}">
				<td>${item['ub_user_no']}</td>
				<td>${item['WITHDRAW']}</td>
				<td>${item['WITHDRAW_ACT']}</td>
				<td>
					<c:forEach items="${info.withdrawTypes}" var="item_u" varStatus="s">
						<c:if test="${item_u.code==item['WITHDRAW_TYPE']}">${item_u.name}</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${info.withdrawStatus}" var="item_u" varStatus="s">
						<c:if test="${item_u.code==item['STATUS']}">${item_u.name}</c:if>
					</c:forEach>
				</td>
				<td><f:formatDate value="${item['CREATED_TIME']}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><f:formatDate value="${item['UPDATED_TIME']}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${item['UPDATED_BY']}</td>
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
    
