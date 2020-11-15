package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.dto.MemberDto;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("/JoinController")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/signUp.jsp");		
		rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//req 먼저 선언
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		//getParameter from signUp.jsp return-type is String.
		
		MemberDao mDao=MemberDao.getInstance();
		//싱글톤으로 MemberDao 생성.
		MemberDto mDto=new MemberDto();
		//그릇 new 객체 만듬.
		mDto.setId(id);
		mDto.setPw(pw);
		mDto.setName(name);
		mDto.setEmail(email);
		//getParametervalue 저장.
		int joinResult=mDao.join(mDto);
		//MemberDao의  method 실행. 그 값은 int joinResult에 저장. 잘되면 1(true) 안되면(false) else로.
		
		if(joinResult==1) { 
			HttpSession session=req.getSession();
			session.setAttribute("sessionID",id);
			//id값 sessionID에 넣음. index.jsp에서 getAttribute로 확인함.Object가 변환형이므로 String로 변환해서 획득함.
			res.sendRedirect("home.com");
			//javascript의 location.href='' 기능등 자바스크립트 다 사용불가.WEB-INF는 WAS만 접근가능 그리고 javascript alert 기능 사용시 같이 있는 자바코드 실행안됨.JAVASCRIPT 사용을 원할시 중간 브릿지를 두어야한다함. 가입 signUp.jsp>가입축하  new file>메인 index.jsp.
			//
		}
		else {
			res.setContentType("text/html; charset=utf-8");
			//PrintWriter보다 먼저 선언.
			PrintWriter out=res.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 이미 존재합니다');");
			out.println("history.back(-1);");
			out.println("</script>");
			out.flush(); // out batch로 출력.
			out.close();

	}
	}
}
