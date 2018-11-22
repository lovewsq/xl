<%@ page pageEncoding="utf-8" import="entity.*"  %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="./css/userAdmin.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
<body>
		<div class="container_box">
		<div class="main_title">用户管理</div>
		<div  class="main_body">
			<div class="nav_title autoH color_909090">
				<label>用户管理</label><span class="jiantou"></span><label class="color_0e6fb6">修改信息</label>
			</div>
			<%
				//获取当前需要修该的对象;
				Admin a=(Admin) request.getAttribute("a");
		    %>
			<div class="shade_content">
				<form method="post" action="modify.do">	
					<div>
						<label>ID:</label>
						<span>
							<input type="text" name="id"  value=<%=a.getId() %>>
						</span>
					</div>
					<div>
						<label>用户名:</label>
						<span>
							<input type="text" name="uname" value=<%=a.getUname() %>>
						</span>
					</div>
					<div>
						<label>真实姓名:</label>
						<span>
							<input type="text" name="name" value=<%=a.getName()%>>
						</span>
					</div>
					<div>
						<label>密码:</label>
						<span>
							<input type="password" name="pwd" value=<%=a.getPassword() %>>
						</span>
					</div>
					<div class="o_btns">
						<input class="save" type="submit" value="保存">
						<input class="cancel" type="button" value="取消">
					</div>
				</form>
			</div>
		</div>
		</div>

</div>
</div>				
</body>
</html>










