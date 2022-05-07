<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/shopstandard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>图书修改</title>
	<style type="text/css">
		th, td{
			width: 130px;
			height: 36px;
			font-size: 14px;
			line-height: 36px;
			text-align: center;
			overflow: hidden;
		}

		table{
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
					<li class="rows" id="row">图书修改</li>
				</ul>
				<div class="content">
					<table>
						<tr><th>ISBN号</th><th>图书名称</th><th>原价</th>
						<th>折扣</th><th>货存量</th><th></th><th></th></tr>
						<s:iterator value="#session.bookshop">
						<tr>
							<td><s:property value="Bnumber"/></td>
							<td><s:property value="Bname"/></td>
							<td><s:property value="Bprice"/></td>
							<td><s:property value="Idiscount"/></td>
							<td><s:property value="Inumgoods"/></td>
							<td><a href="ChangeBook.action?id=<s:property value="Bnumber"/>">修改</a></td>
							<td><a href="DeleteBook.action?id=<s:property value="Bnumber"/>">删除</a></td>
						</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</center>
	</div>
</body>
</html>