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
		alertMsg.confirm("如果导出的数据很多，速度会很慢，请耐心等待！确认导出订单数据吗？", {
			okCall: function(){
				var url = "<%=path %>/order/course/downloadOrders.do";
				url = url + "?username=" + $("#username").val();
				url = url + "&centre_id=" + $("#centre_id").val();
				url = url + "&order_code=" + $("#order_code").val();
				url = url + "&course_type=" + $("#course_type").val();
				url = url + "&course_id=" + $("#course_id").val();
				url = url + "&authorize_course_time_id=" + $("#authorize_course_time_id").val();
				url = url + "&course_start_time=" + $("#course_start_time").val();
				url = url + "&course_end_time=" + $("#course_end_time").val();
				url = url + "&startTime=" + $("#startTime").val();
				url = url + "&endTime=" + $("#endTime").val();
				url = url + "&operationStartTime=";
				url = url + "&operationEndTime=";
				url = url + "&order_status=" + $("#order_status").val();
				
				window.location.href=url;
			}
		});
	};
	
</script>

<div class="pageHeader">

	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="<%=path %>/order/search.do" method="post">
	<input type="hidden" name="resourceStatus" value=""/>
	<input type="hidden" name="pageNum" value="${info.currentPage}" />
	<input type="hidden" name="numPerPage" value="${info.numPerPage}" /><!--【可选】每页显示多少条-->
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					下单账户：
				</td>
				<td>
					<input type="text" name="user_c_phone" value="${info.paramMap['user_c_phone']}" />
				</td>
				<td>
					订单号：
				</td>
				<td>
					<input type="text" name="odd_num" value="${info.paramMap['odd_num']}" />
				</td>
				<td>
					状态：
				</td>
				<td>
					<select name="status" class="combox">
						<option value="">请选择</option>
						<c:forEach items="${info.consultStatusList}" var="p">
							<option <c:if test="${p.code eq info.paramMap['status']}">selected</c:if> value="${p.code}">${p.name}</option>	
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					下单日期：
				</td>
				<td>
					<input type="text" class="date" name="start_booking_time" size="7" value="${param.start_booking_time}" readonly="true" />
					<input type="text" class="date" name="end_booking_time" size="7" value="${param.end_booking_time}" readonly="true" />
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
			<li><a class="edit" href="<%=path %>/order/forwardDetails.do?id={id}" target="navTab" rel="orderDetails"><span>查看</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="javascript:void(0)" onclick="downloadExcel()"><span>导出完整订单</span></a></li>
		</ul>
	</div>

	<table class="table" style="width:890px" layoutH="165">
		<thead>
			<tr>
				<th width="100px">下单账户</th>
				<th width="120px">客户姓名</th>
				<th width="150px">订单号</th>
				<th width="200px">下单日期</th>
				<th width="100px">订单金额</th>
				<th width="100px">订单状态</th>
				<th width="120px">咨询师姓名</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${info.data}" var="item" varStatus="s">
			<tr target="id" rel="${item['ID']}">
				<td>${item['c_user_phone']}</td>
				<td>${item['ucr_name_m']}</td>
				<td>${item['ODD_NUM']}</td>
				<td><f:formatDate value="${item['CREATED_TIME']}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${item['PRICE']}</td>
				<td>
					<c:forEach items="${info.consultStatusList}" var="item_u" varStatus="s">
						<c:if test="${item_u.code==item['STATUS']}">${item_u.name}</c:if>
					</c:forEach>
				</td>
				<td>${item['b_user_name']}</td>
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
    
