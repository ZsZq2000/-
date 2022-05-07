<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/shopstandard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>图书添加</title>
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

		.error{
			height: 24px;
			font-size: 12px;
			line-height: 24px;
			color: red;
		}
	</style>
</head>
<body>
	<s:set var="errorBook" value="#session.errorBook"/>
	<div id="room">
		<div class="tap">
			<div class="item">网上书店系统</div>
			<div class="user">o(0 v 0)o</div>
		</div>
		<center>
			<div id="body">
				<ul class="list">
					<li class="rows" id="row">图书添加</li>
				</ul>
				<div class="content">
					<div class="error">
						<s:if test="#errorBook != null">
    						<s:property value="#session.errorBook"/>
    					</s:if>
					</div>
					<form action="InsertBook" method="post">
						<p>ISBN号:<p><input type="text" class="text" placeholder="版本号为13位数字" name="book.Bnumber" required="required" maxlength="13" /><br>
						<p>书名:</p><input type="text" class="text" name="book.Bname" required="required" maxlength="30" /><br>
						<p>主要作者:</p><input type="text" class="text" name="book.Bwriter" required="required" maxlength="20"/><br>
						<p>出版社:</p><input type="text" class="text" name="book.Bpublish" required="required" maxlength="30"/><br>
						<p>原价:</p><input type="text" class="text" name="book.Bprice" required="required"/><br>
						<input type="submit" class="button" value="提交"/>
						<input type="reset" class="button" value="重置"/>
					</form>
				</div>
			</div>
		</center>
	</div>
</body>
</html>