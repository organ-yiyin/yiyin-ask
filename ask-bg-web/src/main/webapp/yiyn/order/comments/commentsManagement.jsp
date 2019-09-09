<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

</script>

<div class="pageHeader">

	<form id="pagerForm" onsubmit="return navTabSearch(this)" action="<%=path %>/comments/search.do" method="post">
	<input type="hidden" name="resourceStatus" value=""/>
	<input type="hidden" name="pageNum" value="${info.currentPage}" />
	<input type="hidden" name="numPerPage" value="${info.numPerPage}" /><!--【可选】每页显示多少条-->
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					订单号：
				</td>
				<td>
					<input type="text" name="odd_num" value="${info.paramMap['odd_num']}" />
				</td>
				<td>
					下单手机号：
				</td>
				<td>
					<input type="text" name="user_c_phone" value="${info.paramMap['user_c_phone']}" />
				</td>
			</tr>
			<tr>
				<td>
					咨询师姓名：
				</td>
				<td>
					<input type="text" name="user_b_name" value="${info.paramMap['user_b_name']}" />
				</td>
				<td>
					咨询师电话：
				</td>
				<td>
					<input type="text" name="user_b_phone" value="${info.paramMap['user_b_phone']}" />
				</td>
				<td>
					星级(区间)：
				</td>
				<td>
					<select name="start_star" class="combox">
						<option value="">从该星级起</option>
						<c:forEach items="${info.commentsStars}" var="p">
							<option <c:if test="${p.code eq info.paramMap['start_star']}">selected</c:if> value="${p.code}">${p.name}</option>	
						</c:forEach>
					</select>
					<select name="end_star" class="combox">
						<option value="">到该星级止</option>
						<c:forEach items="${info.commentsStars}" var="p">
							<option <c:if test="${p.code eq info.paramMap['end_star']}">selected</c:if> value="${p.code}">${p.name}</option>	
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
			<li><a class="edit" href="<%=path %>/comments/forwardDetails.do?id={id}" target="navTab" rel="commentDetails"><span>修改</span></a></li>
		</ul>
	</div>

	<table class="table" style="width:820px" layoutH="165">
		<thead>
			<tr>
				<th width="150px">订单号</th>
				<th width="120px">下单手机号</th>
				<th width="120px">咨询师姓名</th>
				<th width="120px">咨询师电话</th>
				<th width="80px">星级</th>
				<th width="80px">是否隐藏</th>
				<th width="150px">评价时间</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${info.data}" var="item" varStatus="s">
			<tr target="id" rel="${item['ID']}">
				<td>${item['cs_odd_num']}</td>
				<td>${item['uc_user_phone']}</td>
				<td>${item['ub_user_name']}</td>
				<td>${item['ub_user_phone']}</td>
				<td>${item['STARS']}星</td>
				<td>
					<c:forEach items="${info.yesOrNoTypes}" var="item_u" varStatus="s">
						<c:if test="${item_u.code==item['IS_HIDDEN']}">${item_u.text}</c:if>
					</c:forEach>
				</td>
				<td><f:formatDate value="${item['CREATED_TIME']}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
    
