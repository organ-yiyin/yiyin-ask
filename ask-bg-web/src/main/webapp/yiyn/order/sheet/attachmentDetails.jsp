<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%= path %>/widget/ajaxfileuploade/ajaxfileupload.js"></script>

<script>
	var headImageOnFocus= function(){
		$("#head_image_file").click();
	}
	
	var uploadHeadeImage = function(){
		//console.info($("#head_image_file").val());
		if(!$("#head_image_file").val()){
			alertMsg.error("请选择要上传的图片");
			return;
		}
		
		$.ajaxFileUpload({
		       url:'<%=path%>/attachment/upload.do',//处理图片脚本
		       secureuri :false,
		       fileElementId :'head_image_file',//file控件id
		       data : {
		    	   attachment_bucket:2
		       },
		       dataType : 'json',
		       success : function (data, status){
		    	   if(data.statusCode == "200"){
		    		   //$("#img_oss_url").attr("src",data.data);
			    	   $("#oss_url").val(data.data);
		    	   }
		    	   else{
		    		   alertMsg.error(data.message);
		    	   }
		       },
		       error: function(data, status, e){
		           alert(e);
		       }
		});
	}
	
</script>

<div class="pageContent">
	<form method="post" action="<%=path %>/order/attachment/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		
		<input type="hidden" name="object_type" id="object_type" value="${info.object_type}"/>
		<input type="hidden" name="object_id" id="object_id" value="${info.object_id}"/>
		<div class="pageFormContent" layoutH="56">
			<dl class="">
				<dt>附件名称：</dt>
				<dd>
					<input type="text" name="attachment_name" id="attachment_name" class="required" value="${info.attachment_name}" />
				</dd>
			</dl>
			<dl>
				<dt>附件类型：</dt>
				<dd>
					<select class="combox required" name="attachment_type" id="attachment_type" class="required">
 						<option value="">请选择</option>
						<c:forEach items="${info.attachmentTypes}" var="item" varStatus="s">
							<option value="${item.code}" <c:if test="${item.code==info.attachment_type}">selected</c:if>>${item.name}</option>
						</c:forEach>
 					</select>
				</dd>
			</dl>
			<dl class="">
				<dt>附件地址：</dt>
				<dd>
					<input type="text" id="oss_url" name="oss_url" size="80" placeholder="点击此输入框上传文件" class="required" value="${info.oss_url}" readonly onclick="headImageOnFocus()"/>
					<input style="display:none" class="imageUrl" name="head_image_file" id="head_image_file" type="file" onchange="uploadHeadeImage()" />
					<img src="" id="img_oss_url">
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>附件描述：</dt>
				<dd>
					<textarea name="remark" id="remark" cols="80" rows="8">${info.remark}</textarea>
				</dd>
			</dl>
			
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