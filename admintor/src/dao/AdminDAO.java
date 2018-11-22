package dao;

import java.util.List;

import entity.Admin;

public interface AdminDAO {
	//曾
	public void add(Admin a) throws Exception;
	//删
	public void deleById( int id) throws Exception;
	//改
		//根据id查询
	public Admin findById(int id) throws Exception;
		//修改
	public void modify (Admin admin) throws Exception;
	//查
	public List<Admin> findAll() throws Exception;
	//根据用户名查找记录
	public Admin findByUname(String uname) throws Exception;
}
 