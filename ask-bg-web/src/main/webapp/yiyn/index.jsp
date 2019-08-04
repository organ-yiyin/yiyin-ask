<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>懿英教育  后台管理平台</title>

<link href="<%= path %>/dwz_ria/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%= path %>/dwz_ria/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%= path %>/dwz_ria/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%= path %>/dwz_ria/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="<%= path %>/dwz_ria/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="<%= path %>/dwz_ria/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="<%= path %>/dwz_ria/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="<%= path %>/dwz_ria/chart/raphael.js"></script>
<script type="text/javascript" src="<%= path %>/dwz_ria/chart/g.raphael.js"></script>
<script type="text/javascript" src="<%= path %>/dwz_ria/chart/g.bar.js"></script>
<script type="text/javascript" src="<%= path %>/dwz_ria/chart/g.line.js"></script>
<script type="text/javascript" src="<%= path %>/dwz_ria/chart/g.pie.js"></script>
<script type="text/javascript" src="<%= path %>/dwz_ria/chart/g.dot.js"></script>

<script src="<%= path %>/dwz_ria/js/dwz.core.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.database.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.history.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%= path %>/dwz_ria/js/dwz.print.js" type="text/javascript"></script>

<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)
<script src="<%= path %>/dwz_ria/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="<%= path %>/dwz_ria/js/dwz.regional.zh.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GbywuHn1V51AiEbilQKyvDGg"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header" class="divCenter">
			<div class="headerNav">
				<%--<a class="logo" href="javascript:void(0)">标志</a> --%>
				<img src="<%= path %>/images/logo.png" style="width:150px;height:45px;padding-left:20px"/>
				<a href="<%= path %>/j_spring_security_logout" style="padding-left:850px;color:#CCCCCC">退出(<sec:authentication property="principal.username"></sec:authentication>)</a>
			</div>

			<!-- navMenu -->
		</div>
		
		<div class="divCenter">
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">

				<div class="accordion" fillSpace="sidebar">
					
					<div class="accordionHeader">
						<h2><span>Folder</span>系统管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="javascript:void();">用户权限管理</a>
								<ul>
									<li><a href="<%= path%>/sys/user/management.do" target="navTab" rel="/sys/user/management">用户管理</a></li>
								</ul>
							</li>
						</ul>
						<ul class="tree treeFolder">
							<li><a href="<%= path%>/sys/user/forwardPassword.do" target="navTab" rel="/sys/user/management">个人密码修改</a></li>
						</ul>
					</div>
					
				</div>
			</div>
		</div>
	
		 
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<p><a href="https://code.csdn.net/dwzteam/dwz_jui/tree/master/doc" target="_blank" style="line-height:19px"><span></span></a></p>
								<p><a href="http://pan.baidu.com/s/18Bb8Z" target="_blank" style="line-height:19px"></a></p>
							</div>
							<div class="right">
								<p style="color:red"></p>
							</div>
							<p><span></span></p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							
						</div>
						
						<%--
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?width=0&height=430&fansRow=2&ptype=1&skin=1&isTitle=0&noborder=1&isWeibo=1&isFans=0&uid=1739071261&verifier=c683dfe7"></iframe>
						</div>
						 --%>
					</div>
					
				</div>
			</div>
		</div>
		
		</div>
	</div>

	<div id="footer">Copyright &copy; 2015 <a href="demo_page2.html" target="dialog">Yiyn All rights reserved</a></div>

</body>
</html>