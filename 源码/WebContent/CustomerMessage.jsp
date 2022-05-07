<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/standard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>顾客信息</title>
	<style type="text/css">
		.center{
			width: 200px;
			margin-top: 20px;
			text-align: left;
		}

		input{
			margin-top: 5px;
		}

		p{
			margin-top: 5px;
		}

		.button{
			display: inline-block;
			width: 50px;
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

		.text{
			font-size: 14px;
		}

		.error{
			width: 200px;
			height: 24px;
			color: red;
			line-height: 24px;
			font-size: 12px;
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
					<li class="rows" id="row">顾客信息</li>
				</ul>
				<div class="content">
					<div class="error">
						<s:if test="#session.errorUpdate">
							<s:property value="#session.errorUpdate"/>
						</s:if>
					</div>
					<div class="center">
					<p>用户名:</p>
					<s:property value="customer.Cusername"/><br>
					<p>姓名:</p>
					<s:property value="customer.Cname"/><br>
					<p>密码：</p>
					<s:property value="customer.Cpassword"/>
					<form action="CustomerPassword" method="post">	
						<input type="text" class="text" name="customer.Cpassword" maxlength="20">
						<input type="submit" class="button" value="修改"/><br>
					</form>
					<p>性别:</p>
					<s:property value="customer.Csex"/><br>
					<p>积分:</p>
					<s:property value="customer.Cvalue"/><br>
					<p>联系方式:</p>
					<s:property value="customer.Cphone"/>
					<form action="CustomerPhone" method="post">
						<input type="text" class="text" name="customer.Cphone" maxlength="11">
						<input type="submit" class="button" value="修改"><br>
					</form>
					<p>默认地址:</p>
					<s:property value="customer.Clocal"/>
					<form action="CustomerLocal" method="post">
						<input type="text" class="text"  name="customer.Clocal" maxlength="50">
						<input type="submit" class="button" value="修改">
					</form>
					</div>
				</div>
			</div>
		</center>
	</div>
</body>
</html>