<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>店员信息</title>
</head>
<body>
	<p>用户名:</p>
	<s:property value="shop.Susername"/><br>
	<p>姓名:</p>
	<s:property value="shop.Sname"/><br>
	<p>密码：</p>
	<s:property value="shop.Spassword"/>
	<form action="ShopPassword" method="post">	
		<input type="text" name="shop.Spassword" maxlength="20">
		<input type="submit" value="修改"/><br>
	</form>
	<p>性别:</p>
	<s:property value="shop.Ssex"/><br>
	<p>联系方式:</p>
	<s:property value="shop.Sphone"/>
	<form action="ShopPhone" method="post">
		<input type="text" name="shop.Sphone" maxlength="11">
		<input type="submit" value="修改"><br>
	</form>
	<p>书店名称:</p>
	<s:property value="shop.Sshopname"/>
	<form action="ShopShopname" method="post">
		<input type="text" name="shop.Sshopname" maxlength="50">
		<input type="submit" value="修改">
	</form>
	<p>书店地址:</p>
	<s:property value="shop.Slocal"/>
	<form action="ShopLocal" method="post">
		<input type="text" name="shop.Slocal" maxlength="50">
		<input type="submit" value="修改">
	</form>
</body>
</html>