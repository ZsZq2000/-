<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/standard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>确认界面</title>
	<style type="text/css">
		.center{
			width: 200px;
			margin-top: 20px;
			text-align: left;
		}

		p{
			margin-top: 10px;
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
					<li class="rows" id="row">确认界面</li>
				</ul>
				<div class="content">
					<div class="center">
						<p>订单号:</p><s:property value="bookorder.Onumber"/><br>
						<p>ISBN号:</p><s:property value="bookorder.books.Bnumber"/><br>
						<p>合计:</p><s:property value="bookorder.Osumprice"/><br>
						<p>送货地址:</p><br>
						<form action="BuyCertain" method="post">
							<input type="radio" name="num" value="0" checked="checked">默认地址<br>
							<input type="radio" name="num" value="1">重填地址<br>
							<input type="text" name="id" class="text" maxlength="50" placeholder="限于25个字"><br>
							<p>是否采用积分打折:(每本书</p><s:property value="buyvalue.discountValue"/>
							<p>积分换取合计*</p><s:property value="buyvalue.discount"/>)<br>
							<input type="radio" name="pay" value="1" checked="checked">是&nbsp
							<input type="radio" name="pay" value="0">否<br>
							<p>可获得积分:</p><s:property value="bookorder.Ogetvalue"/><br>
							<p>备注:</p><input type="text" class="text" name="bookorder.Oremark" maxlength="30" placeholder="限于15个字"><br>
							<input type="submit" class="button" value="确认">
						</form>
					</div>
				</div>
			</div>
		</center>
	</div>
</body>
</html>