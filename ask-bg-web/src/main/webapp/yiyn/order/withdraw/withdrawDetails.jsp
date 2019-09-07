<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
	
</script>

<div class="pageContent">

		<div class="pageFormContent" layoutH="55">
		<input type="hidden" name="withdraw_id" value="${withdrawPo.id}">
		<div class="tabs" currentIndex="0" eventType="click">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li><a href="javascript:;"><span>提现账户</span></a></li>
						<li><a href="javascript:;"><span>提现信息</span></a></li>
						<li><a href="javascript:;"><span>附件管理</span></a></li>
						<li><a href="javascript:;"><span>日志</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height:100%;">
				<div>
					<dl>
						<dt>咨询师帐号：</dt>
						<dd>
							${userBPo.user_no}
						</dd>
					</dl>
					<dl>
						<dt>咨询师姓名：</dt>
						<dd>
							${userBPo.user_name}
						</dd>
					</dl>
					<dl>
						<dt>咨询师电话：</dt>
						<dd>
							${userBPo.user_phone}
						</dd>
					</dl>
					<dl>
						<dt>咨询师身份证：</dt>
						<dd>
							${userBPo.user_id_num}
						</dd>
					</dl>
				</div>
			</div>
			<div class="tabsContent" style="height:100%;">
				<div>
					<dl>
						<dt>申请账户：</dt>
						<dd>
							${withdrawPo.account_id}
						</dd>
					</dl>
					<dl>
						<dt>提现金额：</dt>
						<dd>
							${withdrawPo.withdraw}
						</dd>
					</dl>
					<dl>
						<dt>实际线下转账金额：</dt>
						<dd>
							${withdrawPo.withdraw_act}
						</dd>
					</dl>
					<dl>
						<dt>手续费：</dt>
						<dd>
							${withdrawPo.service_charge}
						</dd>
					</dl>
					<dl>
						<dt>转账方式：</dt>
						<dd>
							<c:forEach items="${info.withdrawTypes}" var="item_u" varStatus="s">
								<c:if test="${item_u.code==withdrawPo.withdraw_type}">${item_u.name}</c:if>
							</c:forEach>
						</dd>
					</dl>
					<dl>
						<dt>状态：</dt>
						<dd>
							<c:forEach items="${info.withdrawStatus}" var="item_u" varStatus="s">
								<c:if test="${item_u.code==withdrawPo.status}">${item_u.name}</c:if>
							</c:forEach>
						</dd>
					</dl>
				</div>
			</div>
			
			<div class="tabsContent" style="height:100%;">
				<div>
					<div class="panelBar">
						<ul class="toolBar">
							<li><a class="add" href="<%=path %>/withdraw/attachment/forwardNewDetails.do?withdraw_id=${withdrawPo.id}" target="dialog" rel="newAttachment" mask="true"  width="900" height="400"><span>新增</span></a></li>
							<li class="line">line</li>
							<li><a class="delete" href="<%=path %>/withdraw/attachment/delete.do?id={attachment_id}" target="ajaxTodo" title="确定要删除吗？"><span>删除</span></a></li>
							<li class="line">line</li>
						</ul>
					</div>
					
					<table class="table" style="width:750px">
						<thead>
							<tr>
								<th width="200px">附件名称</th>
								<th width="150px">附件类型</th>
								<th width="400px">附件地址</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${attachments}" var="item" varStatus="s">
							<tr target="attachment_id" rel="${item.id}">
								<td>${item.attachment_name}</td>
								<td>
									<c:forEach items="${info.attachmentTypes}" var="item_u" varStatus="s">
										<c:if test="${item_u.code==item.attachment_type}">${item_u.name}</c:if>
									</c:forEach>
								</td>
								<td><a href="${item.oss_url}" target="_blank">${item.oss_url}</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<br>
				</div>
			</div>
			
			<div class="tabsContent" style="height:100%;">
				<div>
					<table class="table" style="width:650px">
						<thead>
							<tr>
								<th width="50px">序号</th>
								<th width="300px">操作</th>
								<th width="150px">操作人</th>
								<th width="150px">操作时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${logs}" var="item" varStatus="s">
							<tr>
								<td>${s.index + 1}</td>
								<td>${item.log_desc}</td>
								<td>${item.created_by}</td>
								<td><fmt:formatDate value="${item.created_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<br>
					<br>
				</div>
			</div>
			
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
		</div>
		
		</div>
		
		<div class="formBar">
			<ul>
				<c:if test="${withdrawPo.status eq 1}">
					<a class="button" href="<%=path%>/withdraw/adminConfirmWithdraw.do?id=${withdrawPo.id}" target="ajaxTodo" title="确认审核通过吗？"><span>审核通过</span></a>
					<%-- <a class="button" href="<%=path%>/order/adminCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认驳回提现申请吗？"><span>驳回</span></a> --%>
				</c:if>
				<c:if test="${withdrawPo.status eq 2}">
					<a class="button" href="<%=path%>/withdraw/adminTransfer.do?id=${withdrawPo.id}" target="ajaxTodo" title="确认已上传转账凭证并打款成功吗？"><span>已打款</span></a>
				</c:if>
			</ul>
		</div>
		
</div>
