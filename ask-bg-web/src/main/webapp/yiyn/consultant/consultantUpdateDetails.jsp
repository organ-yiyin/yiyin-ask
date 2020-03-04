<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<form method="post" action="<%=path %>/consultant/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" id="id" value="${info.id}"/>
		
		<div class="pageFormContent" layoutH="56">
			<dl class="nowrap">
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="user_no" class="required" value="${info.user_no}" readonly />
				</dd>
			</dl>
			<dl class="">
				<dt>用户姓名：</dt>
				<dd>
					<input type="text" name="user_name" class="required" value="${info.user_name}"/>
				</dd>
			</dl>
			<dl class="">
				<dt>身份证号：</dt>
				<dd>
					<input type="text" name="user_id_num" value="${info.user_id_num}" />
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>用户手机：</dt>
				<dd>
					<input type="text" name="user_phone" value="${info.user_phone}"/>
				</dd>
			</dl>
			<dl>
				<dt>开户行：</dt>
				<dd>
					<input type="text" name="bank_name" value="${info.bank_name}"/>
				</dd>
			</dl>
			<dl>
				<dt>银行账号：</dt>
				<dd>
					<input type="text" name="bank_account" value="${info.bank_account}"/>
				</dd>
			</dl>
			<dl class="">
				<dt>咨询师性质：</dt>
				<dd>
					<select name="user_type" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.userTypes}" var="p">
							<option <c:if test="${p.code eq info.user_type}">selected</c:if> value="${p.code}">${p.name}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>头像：</dt>
				<dd>
					<input type="text" name="user_headimg" size="70" value="${info.user_headimg}"/>
				</dd>
			</dl>
			<dl class="">
				<dt>接单设置：</dt>
				<dd>
					<select name="order_set" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.orderSets}" var="p">
							<option <c:if test="${p.code eq info.order_set}">selected</c:if> value="${p.code}">${p.name}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl class="">
				<dt>从业年限：</dt>
				<dd>
					<input type="text" name="work_year" class="digits" value="${info.work_year}"/>
				</dd>
			</dl>
			
			<dl class="">
				<dt>咨询类型：</dt>
				<dd>
					<c:forEach items="${info.consultingTypes}" var="p">
						<c:if test="${p.code eq info.advice_type}">
							<input type="hidden" name="advice_type" value="${p.code}"/>
							${p.name}
						</c:if>	
					</c:forEach>
					
					<%-- <select name="advice_type" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.consultingTypes}" var="p">
							<option <c:if test="${p.code eq info.advice_type}">selected</c:if> value="${p.code}">${p.name}</option>	
						</c:forEach>
					</select> --%>
				</dd>
			</dl>
			
			<dl class="">
				<dt>咨询单价：</dt>
				<dd>
					<input type="text" name="advice_val" class="number" value="${info.advice_val}"/>
				</dd>
			</dl>
			
			<dl class="">
				<dt>咨询限量：</dt>
				<dd>
					<input type="text" name="advice_num" class="digits" value="${info.advice_num}"/>
				</dd>
			</dl>
			
			<dl class="">
				<dt>附加订单数（刷单用）：</dt>
				<dd>
					<input type="text" name="add_orders" class="digits required" value="${info.add_orders}" />
				</dd>
			</dl>
			
			<dl class="">
				<dt>是否推荐：</dt>
				<dd>
					<select name="recommend" class="combox">
						<option value="">请选择</option>
						<c:forEach items="${info.yesOrNoTypes}" var="p">
							<option <c:if test="${p.code eq info.recommend}">selected</c:if> value="${p.code}">${p.text}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl class="">
				<dt>是否隐藏：</dt>
				<dd>
					<select name="is_hidden" class="combox required">
						<option value="">请选择</option>
						<c:forEach items="${info.yesOrNoTypes}" var="p">
							<option <c:if test="${p.code eq info.is_hidden}">selected</c:if> value="${p.code}">${p.text}</option>	
						</c:forEach>
					</select>
				</dd>
			</dl>
			
			<dl class="nowrap">
				<dt>二维码分享链接：</dt>
				<dd>
					<input type="text" name="share_link" size="70" value="${info.share_link}"/>
				</dd>
			</dl>
			
			<dl class="nowrap">
				<dt>用户简介：</dt>
				<dd>
					<textarea name="user_desc" cols="80" rows="8">${info.user_desc}</textarea>
				</dd>
			</dl>
			
			<%-- <dl class="nowrap">
				<dt>标签列表：</dt>
				<dd>
					<table class="table" targetType="dialog" width="370px">
						<thead>
							<tr>
								<th width="70px">标签值</th>
								<th width="300px">标签名称</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<c:forEach items="${tagList}" var="tag">
								<td>${tag.value}</td>
								<td>${tag.name}</td>
							</c:forEach>
							</tr>
						</tbody>
					</table>
				</dd>
			</dl> --%>
			
			<dl class="nowrap">
				<dt>标签列表新：</dt>
				<dd>
					<table class="table" targetType="dialog" width="370px">
						<thead>
							<tr>
								<th width="70px">选项</th>
								<th width="70px">标签值</th>
								<th width="300px">标签名称</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${info.metaTags}" var="metaTag">
								<tr>
								<c:set var="isTagSelected" value="false"/>
								<c:forEach items="${tagList}" var="tag">
									<c:if test="${metaTag.value eq tag.value}"><c:set var="isTagSelected" value="true"/></c:if>
								</c:forEach>
								
								<td><input type="checkbox" value="${metaTag.value}" name="tags" <c:if test="${isTagSelected == true}">checked</c:if>></td>
								<td>${metaTag.value}</td>
								<td>${metaTag.name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</dd>
			</dl>
			
			<br>
			<br>
			
			
			<div class="divider"></div>
			<dl>
				<dt>创建人员：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="${info.created_by}" />
				</dd>
			</dl>
			<dl>
				<dt>创建时间：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="<fmt:formatDate value="${info.created_time}" pattern="yyyy-MM-dd HH:mm:ss" />" />
				</dd>
			</dl>
			<dl>
				<dt>最新修改人员：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="${info.updated_by}" />
				</dd>
			</dl>
			<dl>
				<dt>最新修改时间：</dt>
				<dd>
					<input readonly="readonly" type="text" size="30" value="<fmt:formatDate value="${info.updated_time}" pattern="yyyy-MM-dd HH:mm:ss" />" />
				</dd>
			</dl>

		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>