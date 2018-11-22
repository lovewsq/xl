package web;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import dao.AdminDAO;
import entity.Admin;
import factory.DaoFactory;

public class ActionServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//修改编码方式
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		//创建流
		PrintWriter out=res.getWriter();
		//获取请求资源路径
		String uri=req.getRequestURI();
		//根君请求资源路径做出相应处理
		String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		//调用工厂方法，创建连接
		AdminDAO dao=(AdminDAO)DaoFactory.getInstance("AdminDao");
		
		if("/list".equals(action)){
			//判断是否有用户登陆过--看session是否有admin
			HttpSession hs=req.getSession();  //获取session
			Object o=hs.getAttribute("admin");  //获取数据
			if(o==null){ //未登录
				//重定向到login页面
				res.sendRedirect("login.jsp");
				return;
			}
			//查出所有的admin
			try {
				List<Admin> ads=dao.findAll();
				//将结果显示到页面--用jsp处理
				//1.绑定参数
				req.setAttribute("ads",ads);
				
				//2.获取转发器
				RequestDispatcher rd= req.getRequestDispatcher("list.jsp");
				
				//3.转发
				rd.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if("/load".equals(action)){
			//根据id修改
			int id=Integer.parseInt(req.getParameter("id"));
			
			try {
				Admin a=dao.findById(id);
				if(a!=null){
					//将记录显示到页面--将a转发到update.jsp
					//绑定
					req.setAttribute("a",a);
					//获取
					RequestDispatcher rd=req.getRequestDispatcher("update.jsp");
					//转发
					rd.forward(req, res);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/modify".equals(action)){
			int id=Integer.parseInt(req.getParameter("id"));
			String uname=req.getParameter("uname");
			String name=req.getParameter("name");
			String password=req.getParameter("pwd");
			
			Admin admin=new Admin();
			//获取页面数据
			admin.setId(id);
			admin.setName(name);
			admin.setPassword(password);
			admin.setUname(uname);
			//修改表中记录
			res.sendRedirect("list.do");
			try {
				dao.modify(admin);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if("/del".equals(action)){
			int id=Integer.parseInt(req.getParameter("id"));
			try {
				dao.deleById(id);
				
				res.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/add".equals(action)){
			//获取页面数据
			String uname=req.getParameter("uname");
			String name=req.getParameter("name");
			String pwd=req.getParameter("pwd");
			Admin a=new Admin();
			a.setUname(uname);
			a.setName(name);
			a.setPassword(pwd);
			//保存入库
			try {
				dao.add(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//重定向
			res.sendRedirect("list.do");
		}else if("/login".equals(action)){
			//获取用户输入的用户名和密码
			String uname=req.getParameter("uname");
			String pwd=req.getParameter("pwd");
			//获取验证码(用户)
			String ucode=req.getParameter("userCode");
			//获取session
			HttpSession session=req.getSession();
			//获取正确验证码
			String rightCode=(String)session.getAttribute("rightCode");
			
			if(!rightCode.equalsIgnoreCase(ucode)){
				req.setAttribute("code_msg","验证码不正确");
				req.getRequestDispatcher("login.jsp").forward(req,res);
				return;  //错误打断
			}
			//根据用户名查找记录
			try {
				Admin a=dao.findByUname(uname);
				//找不到，用户名错误
				if(a==null){
					//绑定
					req.setAttribute("u_msg", "用户名不存在");
					//获取转发器 ,并转发
					req.getRequestDispatcher("login.jsp").forward(req, res);
				}
				//找到，比较密码
				else{
					//成功，登陆成功==>重定向到list
					if(a.getPassword().equals(pwd)){
						//将登陆成功的admin信息保存下来
						HttpSession hs=req.getSession();  //获取session对象
						hs.setAttribute("admin", a);  //绑定
						
						res.sendRedirect("list.do");
					}else{
						//失败，登陆失败
						//绑定
						req.setAttribute("u_msg","密码错误");
						//获取转发器 ,并转发
						req.getRequestDispatcher("login.jsp").forward(req, res);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/loginOut".equals(action)){  //退出登陆功能
			//获取session
			HttpSession hs=req.getSession();
			//删除session
			hs.invalidate();
			//重定向到login
			res.sendRedirect("login.jsp");
		}
		
	}

}





































