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
		//1.设置返回格式
		res.setContentType("image/jpeg");
		//获取流对象
		OutputStream os=res.getOutputStream();
		//2.画图
		BufferedImage image=new BufferedImage(80,40,BufferedImage.TYPE_INT_BGR); //宽 高  颜色类型
		//3.获取画笔
		Graphics g=image.getGraphics();
		//4.添加背景颜色
		Random r=new Random();
		Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));//随机背景颜色
		g.setColor(c);  //画笔沾色
		g.fillRect(0, 0, 80, 40);
		//换画笔颜色
		g.setColor(Color.black);
		//画字母
		String str=getStr(5); //正确的验证码字符串
		HttpSession session=req.getSession();  //将正确的验证码保存起来
		session.setAttribute("rightCode",str);
		//设置字体
		g.setFont(new Font("宋体",Font.BOLD, 20));
		g.drawString(str, 5, 25);
		//画干扰线
		for(int i=0;i<6;i++){
			g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
			g.drawLine(r.nextInt(80), r.nextInt(40),r.nextInt(80), r.nextInt(40));
		}
		//将图片压缩输出
		ImageIO.write(image,"jpeg",os);
	}
	/*
	 * 随机生成字母的方法
	 */
	public String getStr(int length){
		String Str="wqertyuipoasdfgkhlvzxcmvbn123456789";
		Random r=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int index=r.nextInt(Str.length());
			char c=Str.charAt(index);  //根据下标取字符
			sb.append(c);
		}
		return sb.toString();
	}

}




































