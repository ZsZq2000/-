<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/shopstandard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>增添图书</title>
	<style type="text/css">
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

		form{
			margin-top: 20px;
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
					<li class="rows" id="row">增添图书</li>
				</ul>
				<div class="content">
					<form action="AddBook" method="post">
						ISBN号:<input type="text" class="text" name="id" maxlength="13" required="required"><br>
						折扣:<input type="text" class="text" name="imports.Idiscount" required="required"><br>
						库存量:<input type="text" class="text" name="imports.Inumgoods" required="required"><br>
						<input type="submit" class="button" value="提交">
					</form>
				</div>
			</div>
		</center>
	</div>
</body>
</html>