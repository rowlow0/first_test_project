package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BbsDao;
import com.sql.BbsDto;

/**
 * Servlet implementation class BbsViewController
 */
@WebServlet("/BbsViewController")
public class BbsViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsViewController() {
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
		String bbsId=req.getParameter("bbsId");
		BbsDao bbsDao=BbsDao.getInstance();
		BbsDto bbsDto=new BbsDto();
		bbsDao.hitUpdate(bbsId);
		bbsDto=bbsDao.selectById(bbsId);
		//name이 bbsId인 값을 매개 변수로 메소드 실행
		//hitUpdate는 클릭하면 값이 1증가하는 메소드고 selectById는  select*from BBS임.
		req.setAttribute("bbsview", bbsDto);
		// el ${} 사용가능. bbsview.bbsDto값으로 사용.
		//비슷한 느낌1 <jsp:useBean id="this" class="경로"/>
		//비슷한 느낌2 <%=this.value%> 'bbs.jsp' 참조.
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/bbsview.jsp");
		rd.forward(req,res);
}
}
