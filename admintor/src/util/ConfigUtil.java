package util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	static Properties p=new Properties();
	
	static{
		//获取类加载器
		ClassLoader loader=ConfigUtil.class.getClassLoader();
		InputStream ins=loader.getResourceAsStream("config.properties");
		try {
			p.load(ins);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//根据key获取value
	public static String getValue(String key){
		return p.getProperty(key);
		
	}
	//测试
//	public static void main(String[] args) {
//		System.out.println(getValue("AdminDao"));
//	}
}
