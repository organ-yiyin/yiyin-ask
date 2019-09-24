<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="org.apache.commons.lang.math.NumberUtils"%>
<%@ page import="com.yiyn.ask.order.form.ConsultationSheetForm"%>
<%@ page import="java.math.BigDecimal"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%= path %>/widget/mWechat/m-wechat.css" rel="stylesheet" type="text/css" media="screen"/>

<script>
	
</script>

<div class="pageContent">

		<div class="pageFormContent" layoutH="56">
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
					</ul>
				</div>
			</div>
			
			<!-- 下单客户资料  -->
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
								<c:forEach items="${consultantSheet.genders}" var="item_u" varStatus="s">
									<c:if test="${item_u.code==consultRef.sex_b}">${item_u.name}</c:if>
								</c:forEach>
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
			
			<!-- 咨询师资料  -->
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
			</div>
			
			<div class="tabsContent" style="height:100%;">
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
					<dl class="nowrap">
						<dt>微信订单号：</dt>
						<dd>
							${consultantSheet.pay_odd_num}
						</dd>
					</dl>
					<dl>
						<dt>下单时间：</dt>
						<dd>
							<fmt:formatDate value="${consultantSheet.created_time}" pattern="yyyy-MM-dd HH:mm:ss" />
						</dd>
					</dl>
					<dl>
						<dt>订单状态：</dt>
						<dd>
							<c:forEach items="${consultantSheet.consultStatusList}" var="item_u" varStatus="s">
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
						<dd>
						<%
							BigDecimal price = NumberUtils.createBigDecimal(((ConsultationSheetForm)request.getAttribute("consultantSheet")).getPrice());
							if(price != null){
								price = price.multiply(NumberUtils.createBigDecimal("0.7"));
							}
						%>
						<%= price.toPlainString() %>
						</dd>
					</dl>
					<dl>
						<dt>第三方支付类型：</dt>
						<dd>微信支付</dd>
					</dl>
					<dl class="nowrap">
						<dt>操作轨迹：</dt>
						<dd style="width:800px">
							<table class="table" targetType="dialog" width="800px" nowrapTD="false">
								<thead>
									<tr>
										<th width="50px">序号</th>
										<th width="100px">操作</th>
										<th style="width:250px">描述/理由</th>
										<th width="150px">操作人</th>
										<th width="100px">操作人来源</th>
										<th width="150px">操作时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${logs}" var="item" varStatus="s">
									<tr>
										<td>${s.index + 1}</td>
										<td>
											<c:forEach items="${consultantSheet.consultStatusList}" var="item_u" varStatus="s">
												<c:if test="${item_u.code==item.log_type}">${item_u.name}</c:if>
											</c:forEach>
										</td>
										<td>${item.log_desc}</td>
										<td>${item.created_by}</td>
										<td>
											<c:forEach items="${consultantSheet.logUserTypes}" var="item_u" varStatus="s">
												<c:if test="${item_u.code==item.log_user_type}">${item_u.name}</c:if>
											</c:forEach>
										</td>
										<td><fmt:formatDate value="${item.created_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</dd>
					</dl>
				</div>
			</div>
			
			<!-- 咨询内容 -->
			<div class="tabsContent" style="height:100%;">
				<div>
					<dl class="nowrap">
						<dt>主问：</dt>
						<dd>
							${consultantSheet.problem_desc}
						</dd>
					</dl>
					<dl>
						<dt>主问类型：</dt>
						<dd>
							<c:forEach items="${consultantSheet.qus_types}" var="item_u" varStatus="s">
								<c:if test="${item_u.value==consultantSheet.problem_type}">${item_u.name}</c:if>
							</c:forEach>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>主问图片：</dt>
						<dd style="width:500px">
							<table class="table" targetType="dialog" style="width:500px">
								<thead>
									<tr>
										<th width="50px">序号</th>
										<th width="450px">内容(表格中的链接可以点击)</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${consultantSheet.problem_img_list}" var="item_s" varStatus="s">
									<tr>
										<td>${s.index + 1}</td>
										<td>
											<a href="${item_s}" target="_blank">${item_s}</a>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</dd>
					</dl>
					<dl class="nowrap">
						<dt>主问视频：</dt>
						<dd style="width:500px">
							<table class="table" targetType="dialog" style="width:500px">
								<thead>
									<tr>
										<th width="50px">序号</th>
										<th width="450px">内容(表格中的链接可以点击)</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${consultantSheet.problem_video_list}" var="item_s" varStatus="s">
									<tr>
										<td>${s.index + 1}</td>
										<td>
											<a href="${item_s}" target="_blank">${item_s}</a>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</dd>
					</dl>
					<br>
					<div class="divider"></div>
					<br>
					<dl class="nowrap">
						<dt>以下为全部聊天记录：</dt>
						<dd><a class="button" href="<%= path %>/order/forwardWechatDetails.do?id=${consultantSheet.id}" target="dialog" rel="orderWechat" mask="true" title="聊天记录(仿微信)" width="500" height="600"><span>将以下表格中的聊天记录转为仿微信方式查看</span></a><br /><br /></dd>
					</dl>

					<div style="width:900px">
						<table class="table" targetType="dialog" style="width:900px" nowrapTD="false">
							<thead>
								<tr>
									<th width="50px">序号</th>
									<th width="500px">内容</th>
									<th width="100px">内容类型</th>
									<th width="100px">内容发送者</th>
									<th width="150px">时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${consultProcessList}" var="item" varStatus="s">
								<tr>
									<td>${s.index + 1}</td>
									<td>
										${item.content}
									</td>
									<td>
										<c:forEach items="${consultantSheet.contentTypes}" var="item_u" varStatus="s">
											<c:if test="${item_u.code==item.content_type}">${item_u.name}</c:if>
										</c:forEach>
									</td>
									<td>
										<c:forEach items="${consultantSheet.sendTypes}" var="item_u" varStatus="s">
											<c:if test="${item_u.code==item.send_type}">${item_u.name}</c:if>
										</c:forEach>
									</td>
									<td>
										<fmt:formatDate value="${item.created_time}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<!-- 附件管理-->
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
									<c:forEach items="${consultantSheet.attachmentTypes}" var="item_u" varStatus="s">
										<c:if test="${item_u.code==item.attachment_type}">${item_u.name}</c:if>
									</c:forEach>
								</td>
								<td><a href="${item.oss_url}" target="_blank">${item.oss_url}</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
		</div>
		
		</div>
		<div class="formBar">
			<ul>
				<%-- <c:if test="${consultantSheet.status eq 2}">
					<a class="button" href="<%=path%>/order/adminCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认取消订单并且退款吗？"><span>取消订单</span></a>
				</c:if> --%>
				<c:if test="${consultantSheet.status eq 3}">
					<a class="button" href="<%=path%>/order/adminConfirmCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认同意取消订单并且退款吗？"><span>同意取消订单</span></a>
					<a class="button" href="<%=path%>/order/adminRejectCancel.do?id=${consultantSheet.id}" target="ajaxTodo" title="确认驳回取消订单吗？"><span>驳回取消订单</span></a>
				</c:if>
				
			</ul>
		</div>
	
</div>
