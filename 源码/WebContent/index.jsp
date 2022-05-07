<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="./src/css/index.css">
	<style type="text/css">
		input[type="submit"] {border:none;background:none;}
	</style>
	<title>大学校园网上书店</title>
</head>
<body>
	<s:set var="username" value="#session.Cusername"></s:set> 
	<div id="room">
		<div id="top">
			<p class="welcome">
				<em class="item">欢迎光临</em>
				<s:if test="#username != null">
					<a href="ViewCustomerMessage.action" class="login" target="_blank">
						<s:property value="#session.Cusername"/> 
					</a>
					<a href="CustomerOutside.jsp" class="login">退出登录</a>
				</s:if>
				<s:else>
					<a href="CustomerLogin.jsp" class="login">请先登录</a>
					<a href="CustomerRegister.jsp" class="register">注册账号</a>
				</s:else>
			</p>
			<ul class="adver">
				<s:if test="#username != null">
					<li><a href="CheckOrder.action" class="phone">订单查询</a></li>
					<li>(0v0*)</li>
				</s:if>
				<s:else>
					<li><a href="#" class="phone">15123456789</a></li>
				<li>联系方式:</li>
				</s:else>
				<li class="car"><a href="CheckCart.action">购物车</a></li>
				<li class="image"><img src="./src/img/cart.PNG"></li>
			</ul>
		</div>
	
		<div id="header">
			<div class="search">
				<div class="keyword">
					<form action="BookSearch" method="post" class="fm">
						<div class="inpwrapper">
							<!--<input type="text" id="content1" name="book.Bnumber" onblur="this.value='请输入ISBN码或图书名称';" onfocus="if(this.value=='请输入ISBN码或图书名称') this.value='';" value="请输入ISBN码或图书名称"></input> -->
							<input type="text" id="content1" name="book.Bnumber"/>
						</div>
						<div class="btnwrapper">
							<input type="submit" value="搜索"/>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="nav">
			<div class="tap">主要功能</div>
			<ul class="item">
				<li><a href="#" class="tap1">用户信息</a></li>
				<li><a href="#" class="tap1">教材预定</a></li>
				<li class="preread">图书预览</li>
			</ul>
		</div>
		<div id="content">
			<ul class="display1">
				<li>
					<img src="./src/img/book1.jpg">
					<a href="#" class="tap1">汗青堂丛书022·罗马hahahah</a>
					<div class="tap2">著者：[英]玛丽·比尔hahaha</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen118.00</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book2.jpg">
					<a href="#" class="tap1">高等数学（第七版）（上册）</a>
					<div class="tap2">作者：同济大学数学系</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen47.60</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book3.jpg">
					<a href="#" class="tap1">高等数学（第七版）（下册）</a>
					<div class="tap2">作者：同济大学数学系</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen39.30</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book6.jpg">
					<a href="#" class="tap1">计算机网络（第七版）</a>
					<div class="tap2">作者：谢希仁</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen49.00</span>
					</div>
				</li>
			</ul>
			<ul class="display1">
				<li>
					<img src="./src/img/book4.jpg">
					<a href="#" class="tap1">西尔斯当代大学物理 （上册）</a>
					<div class="tap2">作者：邓铁如</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen57.00</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book5.jpg">
					<a href="#" class="tap1">西尔斯当代大学物理 （下册）</a>
					<div class="tap2">作者：邓铁如</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen43.00</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book7.jpg">
					<a href="#" class="tap1">流体力学（第二版）</a>
					<div class="tap2">作者：张也影</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen31.70</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book8.jpg">
					<a href="#" class="tap1">电力系统分析（第四版）（上册）</a>
					<div class="tap2">作者：何仰赞 </div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen20.20</span>
					</div>
				</li>
			</ul>
			<ul class="display1">
				<li>
					<img src="./src/img/book9.jpg">
					<a href="#" class="tap1">电力系统分析（第四版）（下册）</a>
					<div class="tap2">作者：何仰赞</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen36.00</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book10.jpg">
					<a href="#" class="tap1">中国建筑史（第七版）</a>
					<div class="tap2">作者：潘谷西</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen62.00</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book11.jpg">
					<a href="#" class="tap1">星火英语四级真题试卷</a>
					<div class="tap2">作者：汪开虎</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen56.80</span>
					</div>
				</li>
				<li>
					<img src="./src/img/book12.jpg">
					<a href="#" class="tap1">星火英语六级真题试卷</a>
					<div class="tap2">作者：汪开虎</div>
					<div class="tap3">
						<span class="now">&yen59.00</span>
						<span class="past">&yen56.80</span>
					</div>
				</li>
			</ul>
		</div>
		<div id="footer">
			<div class="content">
				<div class="model">
					<h4>
						<div><img src="./src/img/foot1.PNG"></div>
						<span>消费者保障</span>
					</h4>
					<ul>
						<li class="tap1">保障范围</li>
						<li class="tap2">退货退款流程</li>
						<li class="tap1">服务中心</li>
						<li class="tap2">更多特色服务</li>
					</ul>
				</div>
				<div class="model">
					<h4>
						<div><img src="./src/img/foot2.PNG"></div>
						<span>售后服务</span>
					</h4>
					<ul>
						<li class="tap1">新手专区</li>
						<li class="tap2">交易安全警示</li>
						<li class="tap1"><a href="ShopRegister.jsp">免费开店</a></li>
						<li class="tap2"><a href="ShopLogin.jsp">店员登录</a></li>
					</ul>
				</div>
				<div class="model">
					<h4>
						<div><img src="./src/img/foot3.PNG"></div>
						<span>支付方式</span>
					</h4>
					<ul>
						<li class="tap1">信用卡</li>
						<li class="tap2">支付宝</li>
						<li class="tap1">微信</li>
						<li class="tap2">货到付款</li>
					</ul>
				</div>
				<div class="model">
					<h4>
						<div><img src="./src/img/foot4.PNG"></div>
						<span>淘到宝贝</span>
					</h4>
					<ul>
						<li class="tap1">宝贝推荐</li>
						<li class="tap2">微信群聊</li>
						<li class="tap1">教材排行</li>
						<li class="tap2">校园排行</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>