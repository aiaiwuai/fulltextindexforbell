<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<% String base=request.getContextPath().length()==1?request.getContextPath():request.getContextPath()+"/"; %>
<base href="<%=base %>"/>
<title>Page Not Found</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
<!--
body {
	margin-top: 30px;
}
-->
</style></head>
<body bgcolor="#FFFFFF" leftmargin="0" marginwidth="0">
<!-- Save for Web Slices (64910_175949006_2.jpg) -->
<table id="__01" width="999" height="553" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td colspan="2">
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/404_01.jpg" width="408" height="106" alt=""></td>
		<td colspan="2">
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/404_02.jpg" width="591" height="106" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/404_03.jpg" width="312" height="330" alt=""></td>
		<td colspan="2">
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/404_04.jpg" width="398" height="330" alt=""></td>
		<td>
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/404_05.jpg" width="289" height="330" alt=""></td>
	</tr>
	<tr>
		<td colspan="4">
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/404_06.jpg" width="999" height="116" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/&#x5206;&#x9694;&#x7b26;.gif" width="312" height="1" alt=""></td>
		<td>
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/&#x5206;&#x9694;&#x7b26;.gif" width="96" height="1" alt=""></td>
		<td>
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/&#x5206;&#x9694;&#x7b26;.gif" width="302" height="1" alt=""></td>
		<td>
			<img src="http://wlsweb.cn.alcatel-lucent.com/error/images/&#x5206;&#x9694;&#x7b26;.gif" width="289" height="1" alt=""></td>
	</tr>
</table>
<!-- End Save for Web Slices -->
<script type="text/javascript">
	alert("Path error,we'll go to index after 1s.")
	setTimeout("toindex()","1000");
	function toindex(){
		window.location.href="<%=base%>index.action"
	}
</script>
</body>
</html>
