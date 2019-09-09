<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="pageContent">

	<form method="post" action="<%=path%>/sys/user/authority/save.do"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" id="id" value="${info.id}" >
		<div class="pageFormContent" layoutH="56">
			
			<dl class="">
				<dt>用户名：</dt>
				<dd>
					${info.user_no}
				</dd>
			</dl>
			<dl class="">
				<dt>用户姓名：</dt>
				<dd>
					${info.user_name}
				</dd>
			</dl>
			
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>角色授权：</dt>
				<dd>
					<ul class="tree treeFolder treeCheck expand" oncheck="kkk">
						<li>
							<a tname="name" tvalue="value1">订单管理</a>
							<ul>
								<li>
									<jsp:include page="/yiyn/sys/userAuthorityTreeNode.jsp" flush="true">
										<jsp:param name="authorityCode" value="order.management" />
									</jsp:include>
								</li>
								<li>
									<jsp:include page="/yiyn/sys/userAuthorityTreeNode.jsp" flush="true">
										<jsp:param name="authorityCode" value="order.withdraw.management" />
									</jsp:include>
								</li>
								<li>
									<jsp:include page="/yiyn/sys/userAuthorityTreeNode.jsp" flush="true">
										<jsp:param name="authorityCode" value="order.comments.management" />
									</jsp:include>
								</li>
							</ul>
						</li>
						<li>
							<a tname="name" tvalue="value1">服务人员管理</a>
							<ul>
								<li>
									<jsp:include page="/yiyn/sys/userAuthorityTreeNode.jsp" flush="true">
										<jsp:param name="authorityCode" value="userb.management" />
									</jsp:include>
								</li>
								
							</ul>
						</li>
						<li>
							<a tname="name" tvalue="value1">客户管理</a>
							<ul>
								<li>
									<jsp:include page="/yiyn/sys/userAuthorityTreeNode.jsp" flush="true">
										<jsp:param name="authorityCode" value="customer.management" />
									</jsp:include>
								</li>
								
							</ul>
						</li>
						<li>
							<a tname="name" tvalue="value1">营销管理</a>
							<ul>
								<li>
									<jsp:include page="/yiyn/sys/userAuthorityTreeNode.jsp" flush="true">
										<jsp:param name="authorityCode" value="ad.management" />
									</jsp:include>
								</li>
								
							</ul>
						</li>
						<li>
							<a tname="name" tvalue="value1">系统管理</a>
							<ul>
								<li>
									<jsp:include page="/yiyn/sys/userAuthorityTreeNode.jsp" flush="true">
										<jsp:param name="authorityCode" value="sys.user.management" />
									</jsp:include>
								</li>
								
							</ul>
						</li>
					</ul>
						
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
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
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
