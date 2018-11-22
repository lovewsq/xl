package dao;

import java.util.List;

import entity.Admin;

public interface AdminDAO {
	//��
	public void add(Admin a) throws Exception;
	//ɾ
	public void deleById( int id) throws Exception;
	//��
		//����id��ѯ
	public Admin findById(int id) throws Exception;
		//�޸�
	public void modify (Admin admin) throws Exception;
	//��
	public List<Admin> findAll() throws Exception;
	//�����û������Ҽ�¼
	public Admin findByUname(String uname) throws Exception;
}
 