package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * 数据库连接工具类
 */
public class DBUtil {
	//获取连接方法
	public static Connection getConnection(){
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/jsd1807?useUnicode=true&characterEncoding=utf8";
			String user="root";
			String password="1234";
			con=DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) {
//		System.out.println(getConnection());
//	}

}







