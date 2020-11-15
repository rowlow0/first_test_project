package com.dto;
//댓글달기를 추가하고,제목 옆에 댓글1당 1씩 증가하는 기능 구현.이미지댓글도. and 공지사항 and write에 이미지 1순위. 
//이슈+ form의 다름으로 검색과 페이지숫자의 parameter값이 따로놀음. ajax(비동기)로 search와 select 값을 페이지숫자를 바꾸어도 유지시켜야함.
public class Paging {
	private int pageSize,firstPageNo,prevPageNo,startPageNo,pageNo,endPageNo,nextPageNo,finalPageNo,totalCount,prevPageNo10,nextPageNo10;;

	public int getPrevPageNo10() {
		return prevPageNo10;
	}

	public void setPrevPageNo10(int prevPageNo10) {
		this.prevPageNo10 = prevPageNo10;
	}

	public int getNextPageNo10() {
		return nextPageNo10;
	}

	public void setNextPageNo10(int nextPageNo10) {
		this.nextPageNo10 = nextPageNo10;
	}
	
	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getFinalPageNo() {
		return finalPageNo;
	}

	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.makePaging();
	}
	
	private void makePaging() {
		if(this.totalCount==0)return;
		if(this.pageNo==0) this.setPageNo(1);
		if(this.pageSize==0)this.setPageSize(10);
		
		//{} 중괄호 생략되어잇음.
		
		int finalPage=(totalCount+(pageSize -1))/pageSize;
		if(this.pageNo>finalPage)this.setPageNo(finalPage);
		
		if(this.pageNo<0 || this.pageNo>finalPage) this.pageNo=1;
		
		boolean isNowFirst=pageNo==1?true:false;
		boolean isNowFinal=pageNo==finalPage?true:false;
		
		int startPage=((pageNo-1)/10)*10+1;
		int endPage=startPage+10-1;
		
		if(endPage>finalPage) {
			endPage=finalPage;
		}
		this.setFirstPageNo(1);
		if(isNowFirst) {
			this.setPrevPageNo(1);
		}else {
			this.setPrevPageNo((pageNo-1)<1?1:(pageNo-1));
		}
		if(isNowFirst) {
			this.setPrevPageNo10(1);
		}else {
			this.setPrevPageNo10((pageNo-10)<10?1:(pageNo-10));
		}
		this.setStartPageNo(startPage);
		this.setEndPageNo(endPage);
		
		if(isNowFinal) {
			this.setNextPageNo(finalPage);
			this.setNextPageNo10(finalPage);
		}else {
			this.setNextPageNo((pageNo+1)>finalPage?finalPage:(pageNo+1));
			this.setNextPageNo10((pageNo+10)>finalPage?finalPage:(pageNo+10));
		}
		this.setFinalPageNo(finalPage);
		
	}
	}

