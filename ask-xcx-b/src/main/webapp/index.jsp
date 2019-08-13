<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
<title>测试页面</title>
</head>
<body>
<button type="button" onclick="test()">测试语句</button>
</body>
<script type="text/javascript" src="${ctx}/script/jquery-1.11.1.min.js"></script>
<script  type="text/javascript">
var ctx = '${ctx}';
function test(){
	var param={
			no: '22',
		     type:'server'
	};
	var url = ctx + "/consult/getConsultList.x";
	$.ajax({
		type: "get",
		url: url,//提交地址
		data: param,//参数
		dataType: "json", 
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		success: function(data){ //回调方法
			alert(data);
		}
	});
}
</script>
</html>