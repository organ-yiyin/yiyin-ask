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
				var url = "<%=path %>/customer/downloadCustomers.do";
				url = url + "?usre_c_phone=" + $("#usre_c_phone").val();
				url = url + "&name_m=" + $("#name_m").val();
				url = url + "&name_b=" + $("#name_b").val();
				
				window.location.href=url;
			}
		});
	};
	
</script>

<div class="pageHeader">

	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="<%=path %>/customer/search.do" method="post">
	<input type="hidden" name="pageNum" value="${info.currentPage}" />
	<input type="hidden" name="numPerPage" value="${info.numPerPage}" /><!--【可选】每页显示多少条-->
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					下单用户手机号：
				</td>
				<td>
					<input type="text" name="usre_c_phone" id="usre_c_phone" value="${info.paramMap['usre_c_phone']}" />
				</td>
				<td>
					妈妈姓名：
				</td>
				<td>
					<input type="text" name="name_m" id="name_m" value="${info.paramMap['name_m']}" />
				</td>
				<td>
					宝宝姓名：
				</td>
				<td>
					<input type="text" name="name_b" id="name_b" value="${info.paramMap['name_b']}" />
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
			<li><a class="edit" href="<%=path %>/customer/forwardDetails.do?id={id}" target="navTab" rel="customerDetails"><span>查看</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:void(0)" onclick="downloadExcel()"><span>导出</span></a></li>
		</ul>
	</div>

	<table class="table" style="width:670px" layoutH="140">
		<thead>
			<tr>
				<th width="120px">下单用户手机号</th>
				<th width="150px">注册日期</th>
				<th width="100px">妈妈姓名</th>
				<th width="100px">妈妈生日</th>
				<th width="100px">宝宝姓名</th>
				<th width="100px">宝宝生日</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${info.data}" var="item" varStatus="s">
			<tr target="id" rel="${item['ID']}">
				<td>${item.uc_user_phone}</td>
				<td><f:formatDate value="${item.uc_reg_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${item.NAME_M}</td>
				<td>${item.BIRTHDAY_M}</td>
				<td>${item.NAME_B}</td>
				<td>${item.BIRTHDAY_B}</td>
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
    
