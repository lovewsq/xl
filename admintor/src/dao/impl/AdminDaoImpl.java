package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import dao.AdminDAO;
import entity.Admin;

public class AdminDaoImpl implements AdminDAO{

	public List<Admin> findAll() throws Exception {
			//�������ݿ�����
			Connection con=DBUtil.getConnection();
			//ִ�в�ѯ
			String sql="select * from admin";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			List<Admin> list=new ArrayList<Admin>();
			while(rs.next()){
				Admin a=new Admin();
				int id=rs.getInt("id");
				String uname=rs.getString("uname");
				String name=rs.getString("name");
				String password=rs.getString("password");
				a.setId(id);
				a.setUname(uname);
				a.setName(name);
				a.setPassword(password);
				list.add(a);
			}
		//�ر�����
			DBUtil.close(con);
		//�����ҵ������м�¼����װ�ɼ��ϣ�
		return list;
	}

	public Admin findById(int id) throws Exception {
		//��������
		Connection con=DBUtil.getConnection();
		//����id�ҵ�Admin
		String sql="select * from admin where id=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();
		
		Admin a=null;
		if(rs.next()){
			a=new Admin();
			a.setId(rs.getInt("id"));
			a.setUname(rs.getString("uname"));
			a.setName(rs.getString("name"));
			a.setPassword(rs.getString("password"));
		}
		DBUtil.close(con);
		return a;
	}
	//���ݴ����admin����
	public void modify(Admin admin) throws Exception {
		Connection con=DBUtil.getConnection();
		String sql="update admin set uname=?,name=?,password=? where id=?";
		PreparedStatement pst=con.prepareStatement(sql);
		
		pst.setString(1,admin.getUname());
		pst.setString(2,admin.getName());
		pst.setString(3,admin.getPassword());
		pst.setInt(4,admin.getId());
		
		pst.executeUpdate();
		
		DBUtil.close(con);
	}

	public void deleById(int id) throws Exception {
		Connection con=DBUtil.getConnection();
		String sql="delete from admin where id=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1,id);
		pst.executeUpdate();
		//�ر�
		DBUtil.close(con);
	}

	public void add(Admin a) throws Exception {
		Connection con=DBUtil.getConnection();
		String sql="insert into admin values(null,?,?,?)";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setObject(1,a.getUname());
		pst.setObject(2,a.getName());
		pst.setObject(3,a.getPassword());
		pst.executeUpdate();
		//�ر�
		DBUtil.close(con);
	}

	public Admin findByUname(String uname) throws Exception {
		Connection con=DBUtil.getConnection();
		String sql="select * from admin where uname=?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1,uname);
		ResultSet rs=pst.executeQuery();
		Admin a=null;
		if(rs.next()){
			a=new Admin();
			a.setId(rs.getInt("id"));
			a.setUname(rs.getString("uname"));
			a.setName(rs.getString("name"));
			a.setPassword(rs.getString("password"));
		}
		DBUtil.close(con);
		return a;
	}

	

}

























