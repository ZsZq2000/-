<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/shopstandard.css">
	<link rel="stylesheet" type="text/css" href="./src/css/CustomerRegister.css">
	<title>店员注册</title>
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

		.text{
			font-size: 14px;
		}

		h3{
			margin-top: 20px;
			margin-bottom: 10px;
		}
	</style>
</head>
<body>
	<s:set var="errorRegister" value="#session.errorRegister"/>
	<div id="room">
		<div class="tap">
			<div class="item">网上书店系统</div>
			<div class="user">o(0 v 0)o</div>
		</div>
		<center>
			<div id="body">
				<ul class="list">
					<li class="rows" id="row">店员注册</li>
				</ul>
				<div class="content">
					<s:if test="#session.Cusername != null">
						<h3>当前已有用户登录，请先退出</h3>
						<a href="index.jsp">返回主界面</a>
					</s:if>
					<s:else>
						<div class="error">
							<s:if test="#errorRegister != null">
    							<s:property value="#session.errorRegister"/>
    						</s:if>
						</div>
						<form action="ShopRegister" method="post">
							<p>用户名:<p><input type="text" class="text" name="shop.Susername" placeholder="只能且必须包含数字和字母" required="required" maxlength="20" /><br>
							<p>姓名:</p><input type="text" class="text" name="shop.Sname" required="required" maxlength="20" /><br>
							<p>密码:</p><input type="password" class="text" name="shop.Spassword" placeholder="必须包含字母和数字且大于六位" required="required" maxlength="20"/><br>
							<p>性别:</p><input type="radio" name="shop.Ssex" value="男" checked="checked">男&nbsp
							<input type="radio" name="shop.Ssex" value="女">女<br>
							<p>联系方式:</p><input type="text" class="text" name="shop.Sphone" required="required" maxlength="11"/><br>
							<p>书店名:</p><input type="text" class="text" name="shop.Sshopname" required="required" maxlength="50"/><br>
							<p>书店地址:</p><input type="text" class="text" name="shop.Slocal" required="required" maxlength="50"/><br>
							<input type="submit" class="button" value="提交"/>
							<input type="reset" class="button" value="重置"/>
						</form>
					</s:else>
				</div>
			</div>
		</center>
	</div>
</body>
</html>