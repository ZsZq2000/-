<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/ShopGuide.css">
	<title>店员管理界面</title>
</head>
<body>
	<s:set var="username" value="#session.Susername"></s:set>
	<div id="room">
		<div class="tap">
			<div class="item">网上书店-店员管理系统</div>
			<div class="user">
				<span class="name"><a href="ViewShopMessage.action" class="login" target="_blank" style="font-size: 12px;">
					<s:property value="#session.Susername"/></a>
				</span>
				<div class="picture"><img src="./src/img/photo_80.jpg"></div>
				<ul class="exit">
					<li><a href="ShopOutside.jsp" class="login">退出登录</a></li>
				</ul>
			</div>
		</div>
		<center>
			<div id="body">
				<ul class="list">
					<li class="rows"><a href="DealOrder.action">订单处理</a></li>
					<li class="rows"><a href="BookManage.action" class="phone">图书修改</a></li>
					<li class="rows"><a href="BookAdd.jsp">增添图书</a></li>
					<li class="rows" id="row">销售报表</li>
				</ul>
				<div class="content">
					<h2>报表生成</h2>
					<h4>请出入需要生成报表的时间范围</h4>
					<form action="ShowTable" method="post">
						时间上限:<input type="date" class="text" name="postdate" value="2018-09-24" min="2015-09-16"/>&nbsp&nbsp&nbsp
						时间下限:<input type="date" class="text" name="nowdate" value="2018-09-25" min="2015-09-16"/>&nbsp
						<input type="submit" class="text1" value="提交">
					</form>
				</div>
			</div>
		</center>
	</div>
</body>
</html>