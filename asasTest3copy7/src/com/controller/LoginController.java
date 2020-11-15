package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		MemberDao mDao=MemberDao.getInstance();
		int loginResult=mDao.login(id, pw);
		
		
		
		if(loginResult==1) {
			req.setAttribute("loginResult", loginResult);
			HttpSession session=req.getSession();
			session.setAttribute("sessionID", id);
			RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(req, res);
		}else {
			req.setAttribute("loginResult",loginResult);
			RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(req,res);
		}

	}

}
