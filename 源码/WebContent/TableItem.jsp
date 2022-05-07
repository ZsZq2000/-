<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/shopstandard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>查看报表</title>
	<style type="text/css">
		.table{
			display: inline-block;
			width: 150px;
			height: 24px;
			font-size: 12px;
			line-height: 24px;
			text-align: left;
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

		form{
			margin-top: 30px;
		}

		p{
			margin-top: 30px;
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
					<li class="rows" id="row">查看报表</li>
				</ul>
				<div class="content">
					<s:if test="#session.errorTable != null">
						<p><s:property value="#session.errorTable"/></p><br>
					</s:if>
					<s:else>
						<s:if test="#session.table != null">
							<s:iterator value="#session.table">
								<div class="table">ISBN号:
									<s:property value="Bnumber"/>&nbsp</div>
								<div class="table">销售额:
									<s:property value="Osumprice"/>&nbsp</div>
								<div class="table">销量:
									<s:property value="Oquantity"/>&nbsp</div>
							</s:iterator>
						</s:if>	
						<s:else>
							<p>目前并未产生报表</p><br>
						</s:else>
					</s:else>
					<a href="ReturnBack.action">返回主界面</a>
				</div>
			</div>
		</center>
	</div>
</body>
</html>