<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.yiyn.edu.base.constants.AuthorityCode"%>
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
					<% if(AuthorityCode.isAuthorized(AuthorityCode.zixun_buyu_type_management,AuthorityCode.zixun_buyu_management)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>资讯管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.zixun_buyu_type_management)){%>
								<li><a href="<%= path%>/zixun/buyu/zhishi/management.do" target="navTab" rel="/zixun/buyu/zhishi/management">哺育知识管理</a></li>	
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.zixun_buyu_management)){%>
							<li><a href="<%= path%>/zixun/buyu/fenlei/management.do" target="navTab" rel="/zixun/buyu/fenlei/management">哺育知识分类管理</a></li>
							<% } %>
						</ul>
					</div>
					<% } %>
					
					<% if(AuthorityCode.isAuthorized(AuthorityCode.customer_management)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>会员管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.customer_management)){%>
								<li><a href="<%= path%>/customer/customer/management.do" target="navTab" rel="/zixun/customer/customer/management">会员管理</a></li>
							<% } %>	
						</ul>
					</div>
					<% } %>
					
					<% if(AuthorityCode.isAuthorized(AuthorityCode.project_course_management,AuthorityCode.project_item_management)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>项目管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.project_item_management)){%>
							<li><a href="<%= path%>/project/fuwu/management.do" target="navTab" rel="/project/fuwu/management">哺育服务管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.project_course_management)){%>
							<li><a href="<%= path%>/project/kecheng/management.do" target="navTab" rel="/project/kecheng/management">课程设置管理</a></li>
							<% } %>
						</ul>
					</div>
					<% } %>
					
					<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_managment,
							AuthorityCode.staff_examine,
							AuthorityCode.staff_auth,
							AuthorityCode.staff_level,
							AuthorityCode.staff_info,
							AuthorityCode.staff_service_schedule,
							AuthorityCode.staff_course_schedule)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>顾问/讲师管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_managment)){%>
							<%-- <li><a href="<%= path%>/consult/management.htm" target="navTab" rel="consult.management">顾问/讲师管理</a></li> --%>
							<li><a href="<%= path%>/consultant/management.do" target="navTab" rel="consultant.management">顾问/讲师管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_examine)){%>
							<%-- <li><a href="<%= path%>/consult/approvement.htm" target="navTab" rel="consult.approvement">顾问/讲师审批推荐设置</a></li> --%>
							<li><a href="<%= path%>/consultant/approve/management.do" target="navTab" rel="consult.approvement">顾问/讲师审批推荐设置</a></li>
							<% } %>
							
							<%-- <% if(AuthorityCode.isAuthorized(AuthorityCode.staff_auth)){%>
							<li><a href="<%= path%>/consult/coursement.htm" target="navTab" rel="consult.coursement">顾问/讲师授权管理</a></li>
							<% } %> --%>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_auth)){%>
							<li><a href="<%= path%>/consultant/authorize/management.do" target="navTab" rel="consult.authorize">顾问/讲师授权管理</a></li>
							<% } %>
							
							<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_level)){%>
							<li><a href="<%= path%>/fuwu/price/setting.do" target="navTab" rel="consult.pricement">顾问等级/价格设置</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_info)){%>
							<li><a href="<%= path%>/consult/toUpdate.htm" target="navTab" rel="consult.toMyUpdate">顾问/讲师资料维护</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_service_schedule)){%>
							<li><a href="<%= path%>/fuwu/time/management.do" target="navTab" rel="consult.itemmanagement">顾问服务时间/方式维护</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.staff_course_schedule)){%>
							<%--<li><a href="<%= path%>/consult/courseTimemanagement.htm" target="navTab" rel="consult.courseTimemanagement">讲师排课管理</a></li> --%>
							<li><a href="<%= path%>/course/time/management.do" target="navTab" rel="consult.courseTimemanagement">讲师排课管理</a></li>
							<% } %>
						</ul>
					</div>
					<% } %>
					
					<% if(AuthorityCode.isAuthorized(AuthorityCode.center_management,AuthorityCode.center_examine,AuthorityCode.center_update,AuthorityCode.city_management)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>哺育中心管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.center_management)){%>
							<li><a href="<%= path%>/centre/management.do" target="navTab" rel="/centre/profile/management">哺育中心管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.center_examine)){%>
							<li><a href="<%= path%>/centre/examine/management.do" target="navTab" rel="/centre/profile/examine_management">哺育中心审核</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.center_update)){%>
							<li><a href="<%= path%>/centre/forwardUpdateDetails_self.do" target="navTab" rel="/centre/profile/forwardUpdateDetails_self">哺育中心资料</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.city_management)){%>
							<li><a href="<%= path%>/city/management.do" target="navTab" rel="/city/management">城市管理</a></li>
							<% } %>
						</ul>
					</div>
					<% } %>
					
					<% if(AuthorityCode.isAuthorized(AuthorityCode.product_management,AuthorityCode.product_type_management,AuthorityCode.product_spec_management)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>商品管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.product_management)){%>
							<li><a href="<%= path%>/product/management.do" target="navTab" rel="/zixun/product/management">商品管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.product_type_management)){%>
							<li><a href="<%= path%>/product/type/management.do" target="navTab" rel="/product/type/management">商品分类管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.product_spec_management)){%>
							<li><a href="<%= path%>/product/spec/management.do" target="navTab" rel="/product/spec/management">商品属性管理</a></li>
							<% } %>
						</ul>
					</div>
					<% } %>
					
					<% if(AuthorityCode.isAuthorized(AuthorityCode.item_order_management,
							AuthorityCode.course_order_management,
							AuthorityCode.product_order_management,
							AuthorityCode.introduce_management,
							AuthorityCode.comments_management)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>订单管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.item_order_management)){%>
							<li><a href="<%= path%>/order/fuwu/management.do" target="navTab" rel="order.servicelist">服务订单管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.course_order_management)){%>
							<li><a href="<%= path%>/order/course/management.do" target="navTab" rel="order.courselist">课程订单管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.product_order_management)){%>
							<li><a href="<%= path%>/order/product/management.do" target="navTab" rel="order.productlist">商品订单管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.introduce_management)){%>
							<li><a href="<%= path%>/order/introduce/management.do" target="navTab" rel="order.introduceList">推广提现管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.comments_management)){%>
							<li><a href="<%= path%>/comment/management.do" target="navTab" rel="order.commentlist">评价管理</a></li>
							<% } %>
						</ul>
					</div>
					<% } %>
					
					<% if(AuthorityCode.isAuthorized(AuthorityCode.coupons_management,
							AuthorityCode.score_management,
							AuthorityCode.amount_rule,
							AuthorityCode.integral_rule,
							AuthorityCode.activity_management,
							AuthorityCode.ad_management,
							AuthorityCode.about_management,
							AuthorityCode.yiyn_auth_management,
							AuthorityCode.yiyn_promote_management,
							AuthorityCode.yiyn_referrallink_management,
							AuthorityCode.yiyn_message,
							AuthorityCode.yiyn_qa)){%>
					<div class="accordionHeader">
						<h2><span>Folder</span>营销管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<% if(AuthorityCode.isAuthorized(AuthorityCode.coupons_management)){%>
							<li><a href="<%= path%>/marketing/coupon/management.do" target="navTab" rel="#">优惠券管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.score_management)){%>
							<li><a href="<%= path%>/marketing/integral/management.do" target="navTab" rel="#">积分管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.amount_rule)){%>
							<li><a href="<%= path%>/marketing/amountrule/forwardUpdateDetails.do" target="navTab" rel="#">充值金额规则管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.integral_rule)){%>
							<li><a href="<%= path%>/marketing/integralrule/forwardUpdateDetails.do" target="navTab" rel="#">积分/优惠券规则管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.activity_management)){%>
							<li><a href="<%= path%>/marketing/activity/management.do" target="navTab" rel="/marketing/activity/management">最新活动管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.ad_management)){%>
							<li><a href="<%= path%>/marketing/advertising/management.do" target="navTab" rel="/marketing/advertising/management">广告管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.about_management)){%>
							<li><a href="<%= path%>/marketing/about/management.do" target="navTab" rel="/marketing/about/management">关于管理</a></li>
							<% } %>
							<%--
							<% if(AuthorityCode.isAuthorized(AuthorityCode.official_auth_management)){%>
							<li><a href="<%= path%>/" target="navTab" rel="#">官方认证管理</a></li>
							<% } %>
							 --%>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.yiyn_auth_management)){%>
							<li><a href="<%= path%>/student/management.do" target="navTab" rel="/student/management">懿英认证管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.yiyn_promote_management)){%>
							<li><a href="<%= path%>/marketing/promote/management.do" target="navTab" rel="/promote/management">懿英链接推广管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.yiyn_referrallink_management)){%>
							<li><a href="<%= path%>/marketing/referrallink/management.do" target="navTab" rel="/referrallink/management">首页友情链接管理</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.yiyn_message)){%>
							<li><a href="<%= path%>/marketing/message/management.do" target="navTab" rel="/message">懿英通知</a></li>
							<% } %>
							<% if(AuthorityCode.isAuthorized(AuthorityCode.yiyn_qa)){%>
							<li><a href="<%= path%>/marketing/qa/management.do" target="navTab" rel="/qa">咨询问答</a></li>
							<% } %>
						</ul>
					</div>
					<% } %>
					
					
					<div class="accordionHeader">
						<h2><span>Folder</span>系统管理</h2>
					</div>
					<div class="accordionContent">
						<% if(AuthorityCode.isAuthorized(AuthorityCode.user_management,AuthorityCode.role_management,AuthorityCode.auth_management)){%>
						<ul class="tree treeFolder">
							<li><a href="javascript:void();">用户权限管理</a>
								<ul>
									<% if(AuthorityCode.isAuthorized(AuthorityCode.user_management)){%>
									<li><a href="<%= path%>/sys/sys/user/management.do" target="navTab" rel="/sys/sys/user/management">用户管理</a></li>
									<% } %>
									<% if(AuthorityCode.isAuthorized(AuthorityCode.role_management)){%>
									<li><a href="<%= path%>/sys/sys/role/management.do" target="navTab" rel="/sys/sys/role/management">角色管理</a></li>
									<% } %>
									<% if(AuthorityCode.isAuthorized(AuthorityCode.auth_management)){%>
									<li><a href="<%= path%>/sys/sys/authority/management.do" target="navTab" rel="/sys/sys/authority/management">权限管理</a></li>
									<% } %>
									<%--<li><a href="<%= path%>/sys/sys/resource/management.do" target="navTab" rel="/sys/sys/resource/management">资源管理</a></li> --%>
								</ul>
							</li>
						</ul>
						<% } %>
						<ul class="tree treeFolder">
							<%--<li><a href="<%= path%>/sys/user/profile//forwardUserProfile.do" target="navTab" rel="/sys/sys/user/management">用户个人信息</a></li> --%>
							<li><a href="<%= path%>/sys/sys/user/forwardPassword.do" target="navTab" rel="/sys/sys/user/management">个人密码修改</a></li>
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