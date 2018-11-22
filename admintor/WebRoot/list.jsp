<%@ page language="java" import="java.util.*,dao.impl.*,entity.*,util.*,dao.*,factory.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="./css/userAdmin.css">
	<script type="text/javascript" src="./js/jquery.min.js"></script>
</head>
<body>
	<div class="normal">
		<div class="main_title">用户管理</div>
		<div  class="main_body">
			<div class="nav_title autoH color_909090">
				<label>用户管理</label><span class="jiantou"></span><label class="color_0e6fb6">管理员</label>
			</div>
			<!-- 右侧内容 -->
			<div class="content">
				<!-- 选项组 -->
				<div class="options">
					<div class="o_btns">
						<input class="add" value="增加" onclick="location.href='add.html'" />
					</div>
					<a href="loginOut.do" >退出登陆</a>
				</div>
				<div class="c_main">
					<table cellspacing="0" cellpadding="0" width="186" height="82">
						<tr class="thead">
							<td class="col_10">序号</td>
							<td class="col_20">用户名</td>
							<td class="col_20">密码</td>
							<td class="col_20">姓名</td>
							<td class="col_5">操作</td>
						</tr>
						<% 
							//AdminDAO dao=new AdminDaoImpl();
							//查所有admin
							//List<Admin> ads=dao.findAll();
							List<Admin> ads=(List<Admin>) request.getAttribute("ads");
							//遍历
							for(Admin a:ads){
					    %>
							<tr>
							<td class="col_10"><%=a.getId()%></td>
							<td class="col_20"><%=a.getUname() %></td>
							<td class="col_20"><%=a.getPassword() %></td>
							<td class="col_20"><%=a.getName() %></td>
							<td class="col_5"><a class="edit" href="load.do?id=<%=a.getId() %>"></a>/<a href="del.do?id=<%=a.getId() %>" class="delete"></a></td>
							</tr>
						<%
							}
					    %>
							<%-- 
						<tr>
							<td class="col_10">10</td>
							<td class="col_20">red</td>
							<td class="col_20">1234</td>
							<td class="col_20">小红</td>
							<td class="col_5"><a class="edit" href="update.html"></a>/<a href="" class="delete"></a></td>
						</tr>
						<tr>
							<td class="col_10">17</td>
							<td class="col_20">gray</td>
							<td class="col_20">1234</td>
							<td class="col_20">小灰</td>
							<td class="col_5"><a class="edit" href="update.html"></a>/<a href="" class="delete"></a></td>
						</tr>
						<tr>
							<td class="col_10">18</td>
							<td class="col_20">purple</td>
							<td class="col_20">1234</td>
							<td class="col_20">小紫</td>
							<td class="col_5"><a class="edit" href="update.html"></a>/<a href="" class="delete"></a></td>
						</tr>
					--%>
					</table>
					<!-- 分页 -->
					<div class="Page navigation">
					  <ul class="pagination">
					    <li><span class="prev"><a href="">上一页</a></span></li>
					    <li><span><a href="">1</a></span></li>
					    <li><span class="curr"><a href="" >2</a></span></li>
					    <li><span><a href="">3</a></span></li>
					    <li><span><a href="">4</a></span></li>
					    <li><span><a href="">5</a></span></li>
					    <li><span class="next"><a href="">下一页</a></span></li>
					  </ul>
					</div>
				</div>
			</div>
		</div>	
	</div>

</body>
</html>










