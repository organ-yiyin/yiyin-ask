<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/dwz_ria/themes/css/login.css" rel="stylesheet"
	type="text/css" />
<title>后台管理系统</title>
<style>
{
padding
:
0;
 
border
:
0;
 
margin
:
0;
 
outline
:
0;
}
body {
	
}

.wrapper {
	width: 320px;
	height: 350px;
	position: absolute;
	top: 50%;
	margin-top: -175px;
	left: 50%;
	margin-left: -160px;
}

input[type=text],
input[type=password] {
	width: 271px;
	border: solid 1px #d5d5d5;
	height: 42px;
	line-height: 42px;
	color: #999;
	padding-left: 47px;
	margin-bottom: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	-o-border-radius: 5px;
	borde-radius: 5px;
}

input[type=text]:focus,
input[type=password]:focus {
	background-color: #f9f9f9;
	border: solid 1px #99b624;
	color: #333;
}

.iUser,.iKey {
	background-origin: left center;
	background-repeat: no-repeat
}

.iUser {
	background-image: url(<%= path %>/images/admin-login-user.png)
}

.iKey {
	background-image: url(<%= path %>/images/admin-login-ikey.png)
}

input[type=button],
input[type=submit] {
	height: 45px;
	background: #fff;
	width: 320px;
	color: #99b624;
	border: solid 1px #99b624;
	cursor: pointer;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	-o-border-radius: 5px;
	borde-radius: 5px;
}

input[type=button]:hover,
input[type=submit]:hover {
	background: #99b624;
	width: 320px;
	color: #fff;
	margin-bottom: 5px;
}
</style>
</head>
<body>	
	<form action="<%= path %>/j_spring_security_check" method="post">
	<div class="wrapper">
		<div>
			<img src="<%= path %>/images/logox.png" width="320" />
		</div>
		<div>
			<input class="iUser" type="text" name="j_username" value="" />
		</div>
		<div>
			<input type="password" name="j_password" size="20" class="iKey" />
		</div>
		<div>
			<input type="submit" value="登录" />
		</div>
	</div>
	</form>
</body>
</html>