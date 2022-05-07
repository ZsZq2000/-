<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="./src/css/login.css">
	<title>用户登录</title>
</head>
<body>
	<div id="room">
		<center>
			<div id="box">
				<p>用户登录</p>
				<div class="content">
					<s:set var="errorLogin" value="#session.errorLogin"/>
       				<s:if test="#errorLogin != null">
    					<div id="error"><s:property value="#session.errorLogin"/></div>
    				</s:if>
    				<s:else>
    					<div id="error"></div>
    				</s:else>
					<form action="CustomerLogin" method="post">
		    			<div class="inputcontent">   
		         			<input type="text" class="text" name="customer.Cusername" placeholder="用户名" required="required">
		   	 			</div>  
		    			<div class="inputcontent">
		          			<input type="password" class="text" name="customer.Cpassword" placeholder="密码" required="required">  
		    			</div>  
		    			<input type="submit" class="button" value="登录"/>
	    			</form>
				</div>
			</div>
		</center>
		<div id="box"></div>
	</div>
</body>
</html>