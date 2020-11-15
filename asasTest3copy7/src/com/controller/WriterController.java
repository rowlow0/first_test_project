package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BbsDao;
import com.sql.BbsDto;

/**
 * Servlet implementation class WriterController
 */
@WebServlet("/WriterController")
public class WriterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/write.jsp");
		rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		
		HttpSession session=req.getSession();
		
		String sessionID=(String)session.getAttribute("sessionID");
		if(sessionID==null) {
			sessionID="비회원";
		}
		//not use utf-8;
		String bbsCategory=req.getParameter("bbsCategory");
		String bbsTitle=req.getParameter("bbsTitle");
		String bbsContent=req.getParameter("bbsContent");
		//use utf-8;
		
		BbsDao bbsDao=BbsDao.getInstance();
		BbsDto bbsDto=new BbsDto();
		//메모리 생성
		
		bbsDto.setBbsId(bbsDao.nextVal()+1);
		bbsDto.setBbsCategory(bbsCategory);
		bbsDto.setBbsTitle(bbsTitle);
		bbsDto.setBbsContent(bbsContent);
		bbsDto.setId(sessionID);
		
		int wResult=bbsDao.write(bbsDto);
		System.out.println(wResult); // for debug
		res.sendRedirect("bbs.com");
	}

}
