package factory;

import util.ConfigUtil;

public class DaoFactory {
	//根据类型获取对象
	/*
	 * type：接口类型
	 */
	public static Object getInstance(String type){
		Object o=null;
		String className=ConfigUtil.getValue(type);
		//反射实例化
		try {
			o=Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return o;
	}
}
