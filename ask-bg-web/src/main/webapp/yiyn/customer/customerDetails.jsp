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
			<fieldset>
				<legend>妈妈信息</legend>
				<dl>
					<dt>姓名：</dt>
					<dd>
						${info.name_m}
					</dd>
				</dl>
				<dl>
					<dt>生日：</dt>
					<dd>
						${info.birthday_m}
					</dd>
				</dl>
				<dl>
					<dt>特殊情况：</dt>
					<dd>
						${info.special_m}
					</dd>
				</dl>
			</fieldset>
			
			<fieldset>
				<legend>宝宝信息</legend>
				<dl>
					<dt>姓名：</dt>
					<dd>
						${info.name_b}
					</dd>
				</dl>
				<dl>
					<dt>性别：</dt>
					<dd>
						${info.sex_b}
					</dd>
				</dl>
				<dl>
					<dt>出生日期：</dt>
					<dd>
						${info.birthday_b}
					</dd>
				</dl>
				<dl>
					<dt>预产期：</dt>
					<dd>
						${info.edc_b}
					</dd>
				</dl>
				<dl>
					<dt>出生体重：</dt>
					<dd>
						${info.birth_weight_b}
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>特殊情况：</dt>
					<dd>
						${info.special_b}
					</dd>
				</dl>
			</fieldset>
		</div>
		
		<div class="formBar">
			
		</div>
</div>
