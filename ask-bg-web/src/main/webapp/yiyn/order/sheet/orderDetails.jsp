<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script>
	var downloadExcel = function(){
		alertMsg.confirm("确认取消订单吗？", {
			okCall: function(){
				
			}
		});
	};
	
</script>

<div class="pageContent">
	
		<div class="pageFormContent" layoutH="60">
		<input type="hidden" name="consult_sheet_id" value="${consultantSheet.id}">
		<div class="tabs" currentIndex="0" eventType="click">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li><a href="javascript:;"><span>下单客户资料</span></a></li>
						<li><a href="javascript:;"><span>咨询师资料</span></a></li>
						<li><a href="javascript:;"><span>订单详情</span></a></li>
						<li><a href="javascript:;"><span>咨询内容</span></a></li>
						<li><a href="javascript:;"><span>附件管理</span></a></li>
						<li><a href="javascript:;"><span>日志</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height:100%;">
				<div>
					<fieldset>
						<legend>下单账户</legend>
						<dl>
							<dt>客户帐号：</dt>
							<dd>
								${userC.user_no}
							</dd>
						</dl>
						<dl>
							<dt>客户姓名：</dt>
							<dd>
								${userC.user_name}
							</dd>
						</dl>
						<dl class="nowrap">
							<dt>客户电话：</dt>
							<dd>
								${userC.user_phone}
							</dd>
						</dl>
					</fieldset>
					<fieldset>
						<legend>妈妈信息</legend>
						<dl>
							<dt>姓名：</dt>
							<dd>
								${consultRef.name_m}
							</dd>
						</dl>
						<dl>
							<dt>生日：</dt>
							<dd>
								${consultRef.birthday_m}
							</dd>
						</dl>
						<dl>
							<dt>特殊情况：</dt>
							<dd>
								${consultRef.special_m}
							</dd>
						</dl>
					</fieldset>
					
					<fieldset>
						<legend>宝宝信息</legend>
						<dl>
							<dt>姓名：</dt>
							<dd>
								${consultRef.name_b}
							</dd>
						</dl>
						<dl>
							<dt>性别：</dt>
							<dd>
								${consultRef.sex_b}
							</dd>
						</dl>
						<dl>
							<dt>出生日期：</dt>
							<dd>
								${consultRef.birthday_b}
							</dd>
						</dl>
						<dl>
							<dt>预产期：</dt>
							<dd>
								${consultRef.edc_b}
							</dd>
						</dl>
						<dl>
							<dt>出生体重：</dt>
							<dd>
								${consultRef.birth_weight_b}
							</dd>
						</dl>
						<dl class="nowrap">
							<dt>特殊情况：</dt>
							<dd>
								${consultRef.special_b}
							</dd>
						</dl>
					</fieldset>
					
				</div>
			</div>
			<div class="tabsContent" style="height:100%;">
				<div>
					<dl>
						<dt>咨询师帐号：</dt>
						<dd>
							${userB.user_no}
						</dd>
					</dl>
					<dl>
						<dt>咨询师姓名：</dt>
						<dd>
							${userB.user_name}
						</dd>
					</dl>
					<dl>
						<dt>咨询师电话：</dt>
						<dd>
							${userB.user_phone}
						</dd>
					</dl>
					<dl>
						<dt>咨询师身份证：</dt>
						<dd>
							${userB.user_id_num}
						</dd>
					</dl>
				</div>
				<div>
					<dl>
						<dt>订单序号：</dt>
						<dd>
							${consultantSheet.id}
						</dd>
					</dl>
					<dl>
						<dt>内部订单号：</dt>
						<dd>
							${consultantSheet.odd_num}
						</dd>
					</dl>
					<dl>
						<dt>微信订单号：</dt>
						<dd>
							${consultantSheet.pay_odd_num}
						</dd>
					</dl>
					<dl>
						<dt>下单时间：</dt>
						<dd>
							
						</dd>
					</dl>
					<dl>
						<dt>结束时间：</dt>
						<dd>
							
						</dd>
					</dl>
					<dl>
						<dt>订单状态：</dt>
						<dd>
							<c:forEach items="${info.consultStatusList}" var="item_u" varStatus="s">
								<c:if test="${item_u.code==consultantSheet.status}">${item_u.name}</c:if>
							</c:forEach>
						</dd>
					</dl>
					<dl>
						<dt>订单金额：</dt>
						<dd>${consultantSheet.price}</dd>
					</dl>
					<dl>
						<dt>咨询师收入：</dt>
						<dd>${consultantSheet.price*0.7}</dd>
					</dl>
					
				</div>
			</div>
			
			<div class="tabsContent" style="height:100%;">
				<div>
					
				</div>
			</div>
			
			<div class="tabsContent" style="height:100%;">
				<div>
					<div class="panelBar">
						<ul class="toolBar">
							<li><a class="add" href="<%=path %>/order/attachment/forwardNewDetails.do?consult_sheet_id=${consultantSheet.id}" target="dialog" rel="newAttachment" mask="true"  width="900" height="400"><span>新增</span></a></li>
							<li><a class="edit" href="<%=path %>/order/attachment/forwardUpdateDetails.do?id={attachment_id}" target="dialog" rel="updateAttachment" mask="true"  width="900" height="400"><span>新增</span></a></li>
							<li class="line">line</li>
							<li><a class="delete" href="<%=path %>/order/attachment/delete.do?id={attachment_id}" target="ajaxTodo" title="确定要删除吗？"><span>删除</span></a></li>
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
				<c:if test="${consultantSheet.status eq 2}">
					<a class="button" href="<%=path%>/order/adminCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认取消订单并且退款吗？"><span>取消订单</span></a>
				</c:if>
				<c:if test="${consultantSheet.status eq 3}">
					<a class="button" href="<%=path%>/order/adminConfirmCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认同意取消订单并且退款吗？"><span>同意取消订单</span></a>
					<a class="button" href="<%=path%>/order/adminRejectCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认驳回取消订单吗？"><span>驳回取消订单</span></a>
				</c:if>
				
			</ul>
		</div>
	
</div>
