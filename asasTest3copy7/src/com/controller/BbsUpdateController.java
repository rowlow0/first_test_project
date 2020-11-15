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
 * Servlet implementation class BbsUpdateController
 */
@WebServlet("/BbsUpdateController.com")
public class BbsUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
    // url or eclipse f11 == get.
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bbsId=req.getParameter("bbsId");
		
		BbsDao bbsDao=BbsDao.getInstance();
		//싱글톤
		BbsDto bbsDto=new BbsDto();
		//그릇생성(객체생성)
		
		bbsDto=bbsDao.selectById(bbsId);
		//selectById는 select*from BBS where=?문.bbsId을 getParameter한 BBS table 값을 그릇에 모두 저장.
		
		
		req.setAttribute("bbsupdate", bbsDto);
		//그릇은 new 대리자.
		// el 사용하는 설정. &{bbsupdate.'bbsDto의 생성자'}식으로 사용.
		
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/bbsupdate.jsp");
		rd.forward(req, res);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	//'Posted from bbsupdate.jsp 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				req.setCharacterEncoding("utf-8");
				//if req.getParameter is non-english, it`s worked.
				String bbsId=req.getParameter("bbsId");
				String bbsCategory=req.getParameter("bbsCategory");
				String bbsTitle=req.getParameter("bbsTitle");
				String bbsContent=req.getParameter("bbsContent");
				
				BbsDao bbsDao=BbsDao.getInstance();
				BbsDto bbsDto=new BbsDto();
				
				bbsDto.setBbsId(Integer.parseInt(bbsId));
				bbsDto.setBbsCategory(bbsCategory);
				bbsDto.setBbsTitle(bbsTitle);
				bbsDto.setBbsContent(bbsContent);

				bbsDao.update(bbsDto);
				res.sendRedirect("bbs.com");
				//req,res x;
				//위에서 req.setAttrubute을 사용하였는데 그건 'bbsupdate.jsp'에서만 공유됨.
				
	}

}
