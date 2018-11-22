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
		//�޸ı��뷽ʽ
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		//������
		PrintWriter out=res.getWriter();
		//��ȡ������Դ·��
		String uri=req.getRequestURI();
		//����������Դ·��������Ӧ����
		String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		//���ù�����������������
		AdminDAO dao=(AdminDAO)DaoFactory.getInstance("AdminDao");
		
		if("/list".equals(action)){
			//�ж��Ƿ����û���½��--��session�Ƿ���admin
			HttpSession hs=req.getSession();  //��ȡsession
			Object o=hs.getAttribute("admin");  //��ȡ����
			if(o==null){ //δ��¼
				//�ض���loginҳ��
				res.sendRedirect("login.jsp");
				return;
			}
			//������е�admin
			try {
				List<Admin> ads=dao.findAll();
				//�������ʾ��ҳ��--��jsp����
				//1.�󶨲���
				req.setAttribute("ads",ads);
				
				//2.��ȡת����
				RequestDispatcher rd= req.getRequestDispatcher("list.jsp");
				
				//3.ת��
				rd.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if("/load".equals(action)){
			//����id�޸�
			int id=Integer.parseInt(req.getParameter("id"));
			
			try {
				Admin a=dao.findById(id);
				if(a!=null){
					//����¼��ʾ��ҳ��--��aת����update.jsp
					//��
					req.setAttribute("a",a);
					//��ȡ
					RequestDispatcher rd=req.getRequestDispatcher("update.jsp");
					//ת��
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
			//��ȡҳ������
			admin.setId(id);
			admin.setName(name);
			admin.setPassword(password);
			admin.setUname(uname);
			//�޸ı��м�¼
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
			//��ȡҳ������
			String uname=req.getParameter("uname");
			String name=req.getParameter("name");
			String pwd=req.getParameter("pwd");
			Admin a=new Admin();
			a.setUname(uname);
			a.setName(name);
			a.setPassword(pwd);
			//�������
			try {
				dao.add(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//�ض���
			res.sendRedirect("list.do");
		}else if("/login".equals(action)){
			//��ȡ�û�������û���������
			String uname=req.getParameter("uname");
			String pwd=req.getParameter("pwd");
			//��ȡ��֤��(�û�)
			String ucode=req.getParameter("userCode");
			//��ȡsession
			HttpSession session=req.getSession();
			//��ȡ��ȷ��֤��
			String rightCode=(String)session.getAttribute("rightCode");
			
			if(!rightCode.equalsIgnoreCase(ucode)){
				req.setAttribute("code_msg","��֤�벻��ȷ");
				req.getRequestDispatcher("login.jsp").forward(req,res);
				return;  //������
			}
			//�����û������Ҽ�¼
			try {
				Admin a=dao.findByUname(uname);
				//�Ҳ������û�������
				if(a==null){
					//��
					req.setAttribute("u_msg", "�û���������");
					//��ȡת���� ,��ת��
					req.getRequestDispatcher("login.jsp").forward(req, res);
				}
				//�ҵ����Ƚ�����
				else{
					//�ɹ�����½�ɹ�==>�ض���list
					if(a.getPassword().equals(pwd)){
						//����½�ɹ���admin��Ϣ��������
						HttpSession hs=req.getSession();  //��ȡsession����
						hs.setAttribute("admin", a);  //��
						
						res.sendRedirect("list.do");
					}else{
						//ʧ�ܣ���½ʧ��
						//��
						req.setAttribute("u_msg","�������");
						//��ȡת���� ,��ת��
						req.getRequestDispatcher("login.jsp").forward(req, res);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/loginOut".equals(action)){  //�˳���½����
			//��ȡsession
			HttpSession hs=req.getSession();
			//ɾ��session
			hs.invalidate();
			//�ض���login
			res.sendRedirect("login.jsp");
		}
		
	}

}





































