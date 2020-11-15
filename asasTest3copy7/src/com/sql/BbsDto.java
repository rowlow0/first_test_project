package com.sql;

import java.sql.Timestamp;
//VO(Value Object),DTO(Data transport ojbect) 페이지.

//게시판에서 목록을 불러올 떄 <tr><th>작성자</th></tr>에 종속되는 작성자 이름들 (column 값)들인 <tr><td>a</td></tr>,
//<tr><td>b</td></tr>,<tr><td>c</td></tr>을 객체화(구현,실체화)할 떄 a,b,c는 같은 객체(그릇)을 돌려씀.
//단 한번쓴 그릇은 비워야함. 이떄 BbsDto bbsDto=new BbsDto;가 비워줌.
public class BbsDto {
private int bbsId=1,bbsHit=0;
private String bbsTitle,bbsContent,bbsCategory,id;
private Timestamp bbsDate;

public int getBbsId() {
	return bbsId;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("BbsDto [bbsId=");
	builder.append(bbsId);
	builder.append(", bbsHit=");
	builder.append(bbsHit);
	builder.append(", bbsTitle=");
	builder.append(bbsTitle);
	builder.append(", bbsContent=");
	builder.append(bbsContent);
	builder.append(", bbsCategory=");
	builder.append(bbsCategory);
	builder.append(", id=");
	builder.append(id);
	builder.append(", bbsDate=");
	builder.append(bbsDate);
	builder.append("]");
	return builder.toString();
}
public void setBbsId(int bbsId) {
	this.bbsId = bbsId;
}
public int getBbsHit() {
	return bbsHit;
}
public void setBbsHit(int bbsHit) {
	this.bbsHit = bbsHit;
}
public String getBbsTitle() {
	return bbsTitle;
}
public void setBbsTitle(String bbsTitle) {
	this.bbsTitle = bbsTitle;
}
public String getBbsContent() {
	return bbsContent;
}
public void setBbsContent(String bbsContent) {
	this.bbsContent = bbsContent;
}
public String getBbsCategory() {
	return bbsCategory;
}
public void setBbsCategory(String bbsCategory) {
	this.bbsCategory = bbsCategory;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Timestamp getBbsDate() {
	return bbsDate;
}
public void setBbsDate(Timestamp bbsDate) {
	this.bbsDate = bbsDate;
}
}
