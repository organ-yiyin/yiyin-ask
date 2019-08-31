<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
	<form method="post" onsubmit="return validateCallback(this, navTabAjaxDone)" action="<%=path%>/consult/courseTimeAdd/save.json" class="pageForm required-validate">
		<div class="pageFormContent" layoutH="60">
		<input type="hidden" name="consult_sheet_id" value="${consultantSheet.id}">
		<div class="tabs" currentIndex="0" eventType="click">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li><a href="javascript:;"><span>下单客户资料</span></a></li>
						<li><a href="javascript:;"><span>咨询师资料</span></a></li>
						<li><a href="javascript:;"><span>订单详情</span></a></li>
						<li><a href="javascript:;"><span>咨询内容(采集文字、音频、视频、照片内容)</span></a></li>
						<li><a href="javascript:;"><span>附件管理</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height:100%;">
				<div>
					<dl>
						<dt>客户帐号：</dt>
						<dd>
							
						</dd>
					</dl>
					<dl>
						<dt>客户姓名：</dt>
						<dd>
							
						</dd>
					</dl>
					<dl>
						<dt>客户电话：</dt>
						<dd>
							
						</dd>
					</dl>
					<dl>
						<dt>客户身份证：</dt>
						<dd>
							
						</dd>
					</dl>
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
						<dt>总价：</dt>
						<dd>${consultantSheet.price}
						</dd>
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
								<td>${item.oss_url}</td>
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
				<a class="button" href="<%=path%>/order/adminCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认取消订单并且退款吗？"><span>管理员取消订单</span></a>
				<c:if test="${consultantSheet.status eq 2}">
					<a class="button" href="<%=path%>/order/adminCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认取消订单并且退款吗？"><span>管理员取消订单</span></a>
				</c:if>
				<c:if test="${consultantSheet.status eq 3}">
					<div class="button"><div class="buttonContent"><button type="button" class="close">确认退款</button></div></div>
				</c:if>
				
				<c:if test="${order.order_status == 1}">
					<li>
						<a class="button" href="<%=path%>/order/adminApplyCancel.do?order_id=${order.order_id}"  target="_blank" title="确认主动取消订单并且退款？"><span>取消订单</span></a>
					</li>
				</c:if>
				
				<c:if test="${order.order_status == 2}">
					<li>
						<a class="button" href="<%=path%>/order/adminApplyCancel.do?order_id=${order.order_id}" target="_blank" title="确认主动取消订单并且退款？"><span>取消订单</span></a>
					</li>
				</c:if>
				<c:if test="${order.order_status == 6}">
					<li>
						<a class="button" href="<%=path%>/order/rejectCancel.do?order_id=${order.order_id}" target="ajaxTodo" title="确认驳回？">
							<span>驳回</span>
						</a>
					</li>
					<li>
						<a class="button" href="<%=path%>/order/confirmCancel.do?order_id=${order.order_id}" target="_blank" title="确认退款？">
							<span>确认退款</span>
						</a>
					</li>
				</c:if>
				<li>
					
				</li>
			</ul>
		</div>
	</form>
</div>
