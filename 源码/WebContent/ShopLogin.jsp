<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="./src/css/login.css">
	<title>店员登录</title>
	<style type="text/css">
		h3{
			margin-top: 20px;
			margin-bottom: 10px;
		}
	</style>
</head>
<body>
	<div id="room">
		<center>
			<div id="box">
				<p>用户登录</p>
				<div class="content">
					<s:if test="#session.Cusername != null">
						<h3>当前已有用户登录，请先退出</h3>
						<a href="index.jsp">返回主界面</a>
					</s:if>
					<s:else>
						<s:set var="errorLogin" value="#session.errorLogin"/>
       					<s:if test="#errorLogin != null">
    						<div id="error"><s:property value="#session.errorLogin"/></div>
    					</s:if>
    					<s:else>
    						<div id="error"></div>
    					</s:else>
						<form action="ShopLogin" method="post">
		    				<div class="inputcontent">   
		         				<input type="text" class="text" name="shop.Susername" placeholder="用户名" required="required">
		   	 				</div>  
		    				<div class="inputcontent">
		          				<input type="password" class="text" name="shop.Spassword" placeholder="密码" required="required">  
		    				</div>  
		    				<input type="submit" class="button" value="登录"/>
	    				</form>
	    			</s:else>
				</div>
			</div>
		</center>
	</div>
</body>
</html>