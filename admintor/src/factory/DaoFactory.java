package factory;

import util.ConfigUtil;

public class DaoFactory {
	//�������ͻ�ȡ����
	/*
	 * type���ӿ�����
	 */
	public static Object getInstance(String type){
		Object o=null;
		String className=ConfigUtil.getValue(type);
		//����ʵ����
		try {
			o=Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return o;
	}
}
