<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="./src/css/standard.css">
<title>图书信息</title>
<style type="text/css">
	.text{
		margin-top: 20px;
	}

	p{
		margin-top: 10px;
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
					<li class="rows" id="row">图书信息</li>
				</ul>
				<div class="content">
					<div class="text">
						<p>出版号:</p>
						<s:property value="book.Bnumber"/><br>
						<p>图书名称:</p>
						<s:property value="book.Bname"/><br>
						<p>主要作者:</p>
						<s:property value="book.Bwriter"/><br>
						<p>出版社:</p>
						<s:property value="book.Bpublish"/><br>
						<p>原价:</p>
						<s:property value="book.Bprice"/><br>
					</div>
				</div>
			</div>
		</center>
	</div>
</body>
</html>