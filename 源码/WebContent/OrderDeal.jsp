<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/shopstandard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>订单查询</title>
	<style type="text/css">
		.button{
			display: inline-block;
			width: 70px;
			height: 24px;
			background-color: #3385ff;
			border: 2px solid #2d78f4;
			color:white;
			border-radius: 5px;
		}

		.button:hover{
			background-color: #317ef3;
			border: 2px solid #2868c8;
		}

		form{
			margin-top: 20px;
		}

		.table{
			display: inline-block;
			margin-bottom: 5px;
			width: 150px;
			height: 24px;
			font-size: 12px;
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
					<li class="rows" id="row">订单处理</li>
				</ul>
				<div class="content">
					<s:iterator value="#session.ordershop">
						<div class="table">订单号:<s:property value="Onumber"/>&nbsp</div>
						<div class="table">ISBN号:<s:property value="Bnumber"/>&nbsp</div>
						<div class="table">图书名称:<s:property value="Bname"/>&nbsp</div>
						<div class="table">数量:<s:property value="Onumber"/></div><br>
						<div class="table">金额:<s:property value="Osumprice"/>&nbsp</div>
						<div class="table">用户名:<s:property value="Cusername"/>&nbsp</div>
						<div class="table">联系电话:<s:property value="Cphone"/></div><br>
						<s:if test="Osend == true">
							<a href="AcceptOrder.action?num=<s:property value="Onumber"/>">确认发货</a>
						</s:if>
						<s:else>
							<s:if test="Oback == true">
								<a href="ReturnOrder.action?num=<s:property value="Onumber"/>">确认退货</a>&nbsp
								<a href="RefuseOrder.action?num=<s:property value="Onumber"/>">拒绝退货</a>
							</s:if>
							<s:else>
							</s:else>
						</s:else>
					</s:iterator>
				</div>
			</div>
		</center>
	</div>
</body>
</html>