package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BbsDao;

/**
 * Servlet implementation class BbsDeleteController
 */
@WebServlet("/BbsDeleteController")
public class BbsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req,res);
	}

	protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		//한글이 없어서 req.setCharacterEncoding("utf-8"); 없음.
		String bbsId=req.getParameter("bbsId");
		BbsDao bbsDao=BbsDao.getInstance();
		bbsDao.del(Integer.parseInt(bbsId));}
		//bbview(게시판 내용창)에서 form action="xml에서 설정한 주소"가 여기임.
		//그중 name이 bbsId인 값 을 int로 해서('del()'이 int이고 getParameter 기본값은 String임) bbsDao에 있는 .del 실행. 
		catch(Exception e) {}
		finally{res.sendRedirect("bbs.com");}
		//xml 주소가 bbs.com인 서블릿에서는 res,req값 공유 x.
	}
}
