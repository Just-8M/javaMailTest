package com.itshenjin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 功能：发送邮箱功能处理
 * @author itshenjin
 * @author 2018年12月11日
 *
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sessionCode = (String) req.getSession().getAttribute("code");
		System.out.println(sessionCode);
		//  获取session中的验证码
		if(sessionCode != null) {
			//  获取页面提交的验证码
			String inputCode = req.getParameter("code");
			System.out.println("页面提交的验证码:" + inputCode);
			if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
				// 把用户名和密码等一系列信息插入到数据库中，这块很简单的逻辑，暂时没写，后续更新上
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				//  验证成功，跳转成功页面
				req.setAttribute("username", username);
				req.getRequestDispatcher("/success.jsp").forward(req, resp);
			}else {
				//  验证失败
				req.getRequestDispatcher("fail.jsp").forward(req, resp);
			}
		}else {
			//  验证失败
			req.getRequestDispatcher("fail.jsp").forward(req, resp);
		}
		//  移除session中的验证码
		req.removeAttribute("code");
	}
}
