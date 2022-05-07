<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="./src/css/standard.css">
<title>搜索结果</title>
	<style type="text/css">
		table{
			margin-top: 20px;
		}

		th, td{
			width: 130px;
			height: 24px;
			font-size: 16px;
			line-height: 24px;
			text-align: center;
			font-family: arial;
			overflow: hidden;
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
					<li class="rows" id="row">图书搜索</li>
				</ul>
				<div class="content">
					<table>
						<tr><th>ISBN号</th><th>图书名称</th><th>现价</th>
						<th>书店名称</th><th>书店地址</th><th>联系方式</th><th></th></tr>
						<s:iterator value="#session.bookitems">
						<tr>
							<td><s:property value="Bnumber"/></td>
							<td><s:property value="Bname"/></td>
							<td><s:property value="Bnowprice"/></td>
							<td><s:property value="Sshopname"/></td>
							<td><s:property value="Slocal"/></td>	
							<td><s:property value="Sphone"/></td>
							<td><a href="BookMessage.action?id=<s:property value="Bnumber"/>">查看详细信息</a></td>
							<td><a href="BookBuy.action?id=<s:property value="Susername"/>">加入购物车</a></td>
						</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</center>
	</div>
</body>
</html>