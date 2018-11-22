package util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	static Properties p=new Properties();
	
	static{
		//��ȡ�������
		ClassLoader loader=ConfigUtil.class.getClassLoader();
		InputStream ins=loader.getResourceAsStream("config.properties");
		try {
			p.load(ins);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//����key��ȡvalue
	public static String getValue(String key){
		return p.getProperty(key);
		
	}
	//����
//	public static void main(String[] args) {
//		System.out.println(getValue("AdminDao"));
//	}
}
