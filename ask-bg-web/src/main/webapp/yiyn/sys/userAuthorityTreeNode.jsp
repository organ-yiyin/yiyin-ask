<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>
<%@ page import="com.yiyn.ask.base.constants.AuthorityCode"%>
<%@ page import="com.yiyn.ask.sys.po.UserAuthorityPo"%>

<% 
	String param_authorityCode = request.getParameter("authorityCode"); 
	AuthorityCode node = AuthorityCode.findByCode(param_authorityCode);
	List<UserAuthorityPo> authoritys = (List<UserAuthorityPo>)request.getAttribute("userAuthorityPos");
	boolean flag = false;
	for(UserAuthorityPo po : authoritys){
		if(po.getAuthority_code().equals(node.getCode())){
			flag = true;
			break;
		}
	}
	//boolean flag = authoritys.stream().anyMatch(e->e.getAuthority_code().equals(node.getCode()));
%>
<a tname='authItem' tvalue='<%= node.getCode() %>' <%= flag?"checked":"" %>><%= node.getShort_text() %></a>