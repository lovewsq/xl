package web;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//1.���÷��ظ�ʽ
		res.setContentType("image/jpeg");
		//��ȡ������
		OutputStream os=res.getOutputStream();
		//2.��ͼ
		BufferedImage image=new BufferedImage(80,40,BufferedImage.TYPE_INT_BGR); //�� ��  ��ɫ����
		//3.��ȡ����
		Graphics g=image.getGraphics();
		//4.��ӱ�����ɫ
		Random r=new Random();
		Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));//���������ɫ
		g.setColor(c);  //����մɫ
		g.fillRect(0, 0, 80, 40);
		//��������ɫ
		g.setColor(Color.black);
		//����ĸ
		String str=getStr(5); //��ȷ����֤���ַ���
		HttpSession session=req.getSession();  //����ȷ����֤�뱣������
		session.setAttribute("rightCode",str);
		//��������
		g.setFont(new Font("����",Font.BOLD, 20));
		g.drawString(str, 5, 25);
		//��������
		for(int i=0;i<6;i++){
			g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
			g.drawLine(r.nextInt(80), r.nextInt(40),r.nextInt(80), r.nextInt(40));
		}
		//��ͼƬѹ�����
		ImageIO.write(image,"jpeg",os);
	}
	/*
	 * ���������ĸ�ķ���
	 */
	public String getStr(int length){
		String Str="wqertyuipoasdfgkhlvzxcmvbn123456789";
		Random r=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int index=r.nextInt(Str.length());
			char c=Str.charAt(index);  //�����±�ȡ�ַ�
			sb.append(c);
		}
		return sb.toString();
	}

}




































