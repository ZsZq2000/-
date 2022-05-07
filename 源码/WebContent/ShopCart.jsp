<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/standard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>查看购物车</title>
	<style type="text/css">
		.content{
			font-size: 13px;
		}

		.table{
			margin-top: 20px;
			margin-bottom: 5px;
			display: inline-block;
			width: 150px;
			height: 24px;
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
			line-height: 24px;
			text-align: left;
		}
	</style>
</head>
<body>
	<div id="room">
		<div class="tap">
			<div class="item">网上书店系统</div>
			<div class="user">o(0 v 0)o</div>
		</div>
		<center>
			<div id="body">
				<ul class="list">
					<li class="rows" id="row">查看购物车</li>
				</ul>
				<div class="content">
					<s:iterator value="#session.cart">
						<div class="table">订单号:<s:property value="Onumber"/></div>
						<div class="table">ISBN号:<s:property value="Bnumber"/></div>
						<div class="table">图书名称:<s:property value="Bname"/></div><br>
						<div class="table">单价:<s:property value="Bnowprice"/></div>
						<div class="table">书店名称:<s:property value="Sshopname"/></div>
						<div class="table">店家电话:<s:property value="Sphone"/></div><br>
						数量：<a href="SubQuantity.action?num=<s:property value="Onumber"/>">-</a>
						<s:property value="Oquantity"/>
						<a href="AddQuantity.action?num=<s:property value="Onumber"/>">+</a>&nbsp
						<a href="PurchaseOrder.action?num=<s:property value="Onumber"/>">购买</a><br><br>
					</s:iterator>
				</div>
			</div>
		</center>
	</div>
</body>
</html>