<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="pageContent">
	<form method="post" onsubmit="return validateCallback(this, navTabAjaxDone)" action="<%=path%>/consult/courseTimeAdd/save.json" class="pageForm required-validate">
		<div class="pageFormContent" layoutH="60">
		<input type="hidden" name="order_id" value="${order.order_id}">
		<div class="tabs" currentIndex="0" eventType="click">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li><a href="javascript:;"><span>下单客户资料</span></a></li>
						<li><a href="javascript:;"><span>订单详情</span></a></li>
						<li><a href="javascript:;"><span>咨询内容(采集文字、音频、视频、照片内容)</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height:100%;">
				<div>
					<dl>
						<dt>客户帐号：</dt>
						<dd>
							${customer.username}
						</dd>
					</dl>
					<dl>
						<dt>客户姓名：</dt>
						<dd>
							${customer.name}
						</dd>
					</dl>
					<dl>
						<dt>客户电话：</dt>
						<dd>
							${customer.username}
						</dd>
					</dl>
					<dl>
						<dt>客户邮箱：</dt>
						<dd>
							${customer.email}
						</dd>
					</dl>
				</div>
				<div>
					<dl>
						<dt>订单序号：</dt>
						<dd>
							${order.order_id}
						</dd>
					</dl>
					<dl>
						<dt>内部订单号：</dt>
						<dd>
							${order.order_code}
						</dd>
					</dl>
					<dl>
						<dt>订单类型：</dt>
						<dd>
							${order.type}
						</dd>
					</dl>
					<dl>
						<dt>订单状态：</dt>
						<dd>
							${order.status}
						</dd>
					</dl>
					<dl>
						<dt>下单时间：</dt>
						<dd>${order.create_time}</dd>
					</dl>
					<dl>
						<dt>课程售价：</dt>
						<dd>${order.good_price}
						</dd>
					</dl>
					<dl>
						<dt>收费方式：</dt>
						<dd>${order.act_charve_type}
						</dd>
					</dl>
					<dl>
						<dt>预付金额：</dt>
						<dd>${order.act_pre_price}
						</dd>
					</dl>
					<dl>
						<dt>购买数量：</dt>
						<dd>${order.good_number}
						</dd>
					</dl>
					<dl>
						<dt>总价：</dt>
						<dd>${order.netpay}
						</dd>
					</dl>
					<dl>
						<dt>剩余支付金额：</dt>
						<dd>${order.left_pay}
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>订单状态变迁：</dt>
						<dd>
							<c:forEach items="${orderLogs}" var="item" varStatus="p_status">
								${p_status.index + 1} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:formatDate value="${item.operation_time}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:forEach items="${orderStatusFlowLogs}" var="p">
									<c:if test="${p.value==item.operation}">${p.text}</c:if>
								</c:forEach>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${empty item.updater }">
									系统
								</c:if>
								<c:if test="${item.updater != ''}">
									${item.updater}
								</c:if>
								<br>
							</c:forEach>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>备注：</dt>
						<dd>${order.note}
						</dd>
					</dl>
				</div>
				
				<div>
					
				</div>
				
			</div>
			
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
		</div>
		
		</div>
		<div class="formBar">
			<ul>
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
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
