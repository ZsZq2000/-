<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/standard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>退款理由</title>
	<style type="text/css">
		.center{
			width: 200px;
			margin-top: 20px;
			text-align: left;
		}

		form{
			margin-top: 20px;
		}

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
		
		input, textarea{
			margin-top: 10px;
		}

		.text{
			font-size: 14px;
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
					<li class="rows" id="row">退款理由</li>
				</ul>
				<div class="content">
					<div class="center">
						退款理由:<br>
						<form action="ReturnReason" method="post">
							<textarea name="id" rows="3" cols="10" wrap="true" maxlength="15"></textarea><br>
							<input type="submit" class="button" value="提交申请">
						</form>
					</div>
				</div>
			</div>
		</center>
	</div>
</body>
</html>