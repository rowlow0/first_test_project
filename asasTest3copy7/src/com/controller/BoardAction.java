package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BbsDao;
import com.dto.Paging;
import com.sql.BbsDto;

public class BoardAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException {

	    BbsDao bbsDao=BbsDao.getInstance();
		int page=req.getParameter("page")==null?1:Integer.parseInt(req.getParameter("page"));
		String select=req.getParameter("select")==null?"":req.getParameter("select");
		String search=req.getParameter("search")==null?"":req.getParameter("search");
		String tNumber=req.getParameter("tNumber")==null?"":req.getParameter("tNumber");
		
		Paging paging=new Paging();
		paging.setPageNo(page);
		if(tNumber.equals("hund")) {paging.setPageSize(100);}
		else if(tNumber.equals("fit")) {paging.setPageSize(50);}
		else if(tNumber.equals("ten")) {paging.setPageSize(10);}
		else {paging.setPageSize(10);}
		int totalCount=bbsDao.getTotalCount(select,search);
		paging.setTotalCount(totalCount);
		
		/* if(page>1) {page=1+10*(page-1);paging.setPageSize(page+9);} for pagination start with 1;*/
		
		//for pagination (desc,내림차순,큰 숫자 우선) 최신순;
		//별로 좋은 코드는 아닌듯. paging처럼 getter setter하기.
		int startRow=totalCount-page*10+1;
		int endRow=totalCount-page*10+10;
		if(tNumber.equals("hund")) {
			startRow=totalCount-page*100+1;
			endRow=totalCount-page*100+100;
		}
		if(tNumber.equals("fit")) {
			startRow=totalCount-page*50+1;
			endRow=totalCount-page*50+50;
		}
		List<BbsDto> list=bbsDao.getList(startRow,endRow,select,search);
		req.setAttribute("list",list);
		req.setAttribute("paging", paging);
		req.setAttribute("tNumber", tNumber);
	    req.setAttribute("select", select);
	    req.setAttribute("search", search);
		
		
		
	}
}