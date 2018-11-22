<%@ page pageEncoding="utf-8" import="java.util.*"  %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body class="login_body">
	<div class="loginBox">
		<img class="login_logo" src="./images/login_logo.png">
		<div class="loginForm">
			<form action="login.do" method="post">
				<div>
					<label class="fl w_20">昵称：</label>
					<span class="fr w_80"><input type="text" class="text_txt" name="uname"></span>
				</div>
				<div>
					<label class="fl w_20">密码：</label>
					<span class="fr w_80"><input type="password" class="text_txt" name="pwd"></span>
					<%
					String logMsg=(String) request.getAttribute("u_msg");
					%>
					<span class="msg"  style="color:red"><%=logMsg==null?"":logMsg %></span>
				</div>
				<div>
					<label class="fl w_20">验证码：</label>
					<span class="fr w_80 verify"><input type="text" class="text_txt code" name="userCode">
					<img id="num" src="checkCode"/>
					<a href="javascript:;" 
					onclick="document.getElementById('num').src = 'checkCode?t='+(new Date()).getTime()">
					换一张</a>			
					</span>
					<%
					String str=(String) request.getAttribute("code_msg");
					%>
					<span class="msg"  style="color:red"><%=str==null?"":str %></span>
				</div>
				<div>
					<label class="fl w_20"></label>
					<div class="fr w_80">
						<span class="fl"><input type="checkbox" name=""> 下次自动登录</span>
						<a class="fr forget" href="#">忘记密码</a>
					</div>
				</div>
				<div>
					<span class="fr w_80">
						<input type="submit" style="height: 50px;" value="登录">
					</span>
				</div>
			</form>
		</div>
	</div>

</body>
</html>