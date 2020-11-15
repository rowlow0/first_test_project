package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sql.BbsDto;

public class BbsDao {
private Connection con;
private PreparedStatement pstmt;
private ResultSet rs;
private int result=0;
//class bbsDao의 전역변수. 아래에서 ()로 선언된 각각 메소스에서 각각 사용됨.(재사용)

//싱글톤 시작.
private static BbsDao bbsDao;
//static 고정된 상태.class 종료될 때까지 계속 작동함.싱글톤 사용조건중1.
//=new BbsDao(); 삭제.

public BbsDao() {
}
//실행시 기본생성자가 만들어지는데 방지함(매개변수 있는 경우 제외하고는 기본생성자가 자동 생성됨. 단 여기는 private가 달려 생성이 불가능함.).
//이때 getInstance()을 통해 하나의 인스턴스(객체가 실체화됨.메모리먹음.)을 할당.
//	super(); 생략가능.싱글톤 사용조건중1.

public static synchronized BbsDao getInstance() {
	if(bbsDao==null) {
		bbsDao=new BbsDao();
	}
	return bbsDao;
}
//싱크로율(동기화)하기. 멀티쓰레드 동시접근을 막음.단일쓰레드만 접근.
//서블릿이나 jsp등 다른곳에서 class BbsDao을 쓰고싶을 때 BbsDao bbsDao=new BbsDao(); 선언함.

//싱크로율(synchronized) 'LazyHolder' 검색.

//게시판은 캐싱을 통해 서버에 데이터를 임시로 가지고 있고 요청이 오면 db 접근안하고 가지고 있는 데이터로 응답함.
//db 접근시에는 브라우저 request -> 서버 쓰레드 생성  -> 쓰레드는 동일 DAO 객체에 접근하고 서로 다른 커넥션 객체을 받아 사용 -> response.
//즉 싱글톤으로 dao 사용시 동일 DAO 객체, 단일 인스턴스(객체가 소프트웨어로 구현된 실체)로 여러곳에 뿌리는게 가능.
//멀티쓰레드는 채팅같은 것에 사용.()가 동시 작동 원할 때 사용.

//커넥션 객체, 커넥션풀 설정은 톰캣 컨테이너 xml을 수정. 게시판 이용자 컴퓨터의 jvm 환경 동기화등 효율적 기능가능. 관리는 톰캣이 알아서해줌.

//싱글톤 종료.

//DriverManager인 oracle.jdbc.OracleDriver을 연결하는문. 메소드마다 재사용.
public Connection getConnect() {
	String url="jdbc:oracle:thin:@localhost:1521/orcl";
	//oracle이 xe버전이면 xe orcl버전이면 orcl. port는 CMD->LSNRCTL.EXE->STATUS 확인.
	String id="BBS",pw="1234";
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		con=DriverManager.getConnection(url,id,pw);
	}catch(ClassNotFoundException e) {
		//try문시 필수. 간단형태 catch(e){}.
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return con;
	// con은 DriverManager.getConnection(jdbc:oracle:thin:@localhost:1522/orcl,BBS,1234)와 동일.
}

//db연결 종료. 메소드마다 재사용.
public void close(Connection con,PreparedStatement pstmt,ResultSet rs) {
	//void type -> return 없음. close()사용시 매개변수(con,pstmt,rs) 사용 강제. 값이 없으면 오류 발생 필수 입력 값에 null도 가능.
	//public Connection는 return이 Connection이고
	//public int는 return이 int이다.
	//
	if(rs!=null) {
		try {rs.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
}
	if(pstmt!=null) {
		try {
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	if(con!=null) {
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

//
//primary key(not null,unique)이자 게시판 글들을 구별하는 번호 기능을 수행하는 BBSID에 max(bbsid) 쿼리문을 실행해서 최고 숫자을 구하고 반환함. 새 넘버링시 사용.
public int nextVal() {
	
	StringBuffer query=new StringBuffer();
	// sql query '.append' 사용가능.
	query.append("SELECT MAX(BBSID)").append(" FROM BBS");
	//'query.append("SELECT MAX(BBSID) FROM BBS);'와 동일. 띄워쓰기에 주의.
	
	try {
		con=getConnect();
		pstmt=con.prepareStatement(query.toString());
		//jdbc연결.준비상태(sql문) ==(은) 준비된상태.
		//StringBuffer와 StringBuilder는 '.toString()', String으로 변환해서 사용함.
		rs=pstmt.executeQuery();
		//준비된상태.실행 ==(은) 결과.
		//select->executeQuery(); update,delete,insert->executeUpdate(); select만 Query임.
		while(rs.next()) {
			//rs 값 전부 출력.
			result=rs.getInt(1);
			//rs 값은 select max(bbsid) from bbs. 즉 가장 높은 값 1개 특정이므로  'max(bbsid)'을 (1)로 변경.
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(con,pstmt,rs);
	}return result;
}

//write. writer. insert문담당
public int write(BbsDto bbsDto) {
	try {
		boolean iresult=false;
		StringBuffer query=new StringBuffer();
		query.append("INSERT INTO BBS");
		query.append(" VALUES(?,?,?,sysdate,0,?,?)");
		//띄워쓰기 주의
		con=getConnect();
		con.setAutoCommit(false);
		pstmt=con.prepareStatement(query.toString());
		pstmt.setInt(1, bbsDto.getBbsId());
		//1은 첫번째 ?에 넣을거라는거 의미함. 2는 두번째 ?. bbsDto에 저장된 BbsId값 get함. 그리고 pstmt에 set함 . 그리고 execeute, 실행해서 db에 전달함.
		pstmt.setString(2, bbsDto.getBbsTitle());
		pstmt.setString(3, bbsDto.getBbsContent());
		pstmt.setString(4, bbsDto.getBbsCategory());
		pstmt.setString(5, bbsDto.getId());
		result=pstmt.executeUpdate();
		if(result>0) {
			con.commit();
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(con,pstmt,rs);
	}
	return result;
}

//update. bbsupdate. update문담당
public int update(BbsDto bbsDto) {
	con=getConnect();
	StringBuffer query=new StringBuffer();
	query.append("UPDATE BBS SET BBSTITLE=?,BBSCONTENT=?,BBSCATEGORY=? WHERE BBSID=?");
	try {
		pstmt=con.prepareStatement(query.toString());
		pstmt.setInt(4, bbsDto.getBbsId());
		pstmt.setString(1, bbsDto.getBbsTitle());
		pstmt.setString(2, bbsDto.getBbsContent());
		pstmt.setString(3, bbsDto.getBbsCategory());
		result=pstmt.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(con,pstmt,rs);
	}
	return result;
}


//게시판 목록 'bbs.jsp'. select문 담당 list로 전체보기.페이지네이션화 &검색추가.
//sql where column like 'value' 솔로
//sql where column like '%value%' search-all
//sql where column like 'value%' start
//sql where column like '%value' end
public List<BbsDto> selectList(String select,String search){
	//list의 자료는 BbsDto.
	List<BbsDto> list1=new ArrayList<BbsDto>();
	/*String sql="SELECT *FROM BBS ORDER BY BBSID DESC";*/
	StringBuffer query= new StringBuffer();
	
	try {
		con=getConnect();
		if(search.equals("")) {
			query.append("select*from (select row_number() over (order by bbsid) num,b.* from bbs b order by bbsid)b where num between ? and ?");
			pstmt=con.prepareStatement(query.toString());
		}//빈공간 검색시 나오는 page.
		else if(select.equals("category")) {
			query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where bbscategory like ? order by bbsid desc)b) where num between ? and ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");

			//setNString setString 차이없음.
		}
		else if(select.equals("title")) {
			query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where bbstitle like ? order by bbsid desc)b) where num between ? and ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");

		}
		else if(select.equals("content")) {
			query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where bbscontent like ? order by bbsid desc)b) where num between ? and ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");

		}
		else if(select.equals("id")) {
			query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where id like ? order by bbsid desc)b) where num between ? and ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");

		}
		else {
			query.append("select*from (select row_number() over (order by bbsid) num,b.* from bbs b order by bbsid)b where num between ? and ?");
			pstmt=con.prepareStatement(query.toString());

		}// 기본페이지
		
		rs=pstmt.executeQuery();
		while(rs.next()) {
			BbsDto bbsDto=new BbsDto();
			//rs.next()의 값이 while로 여러개 나오는데 bbsDto의 pojo 기능 (constructor,생성자 기능)으로 객체 재생성. 이 문이 밖에 try밖에 선언되면 첫번째 값만 저장되고 두번째 값이 나오는 부분부터는 첫번째 값이 ArrayList을 잠식함.
			bbsDto.setBbsId(rs.getInt("bbsId"));
			//현재 select문으로 bbs table을  *(전체출력) 하고 BBSID 기준으로 desc,내림차순(큰숫자가 위에있고 작은숫자가 아래에있게)출력되어있음.
			//거기서 bbsId의 값을 int로  get하고 값을 bbsDto에 setBbsId로 저장함.
			bbsDto.setBbsTitle(rs.getString("bbsTitle"));
			bbsDto.setBbsContent(rs.getString("bbsContent"));
			bbsDto.setBbsDate(rs.getTimestamp("bbsDate"));
			bbsDto.setBbsHit(rs.getInt("bbsHit"));
			bbsDto.setBbsCategory(rs.getString("bbsCategory"));
			bbsDto.setId(rs.getString("id"));
			list1.add(bbsDto);
			//bbsDto에 저장된 값을 list에 다시 저장.
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		close(con,pstmt,rs);
	}return list1;
}
//dead code

//BBSHIT 조회수. bbsview. bbs에서 title 클릭으로 view로 갈시 bbshit 업데이트문.
public int hitUpdate(String bbsId) {
	con=getConnect();
	String sql="UPDATE BBS SET BBSHIT=BBSHIT+1 WHERE BBSID=?";
	//BBSHIT은 DB에서 초기값이 0임. BBSHIT=0+1 -> 1, BBEHIT=1+1 -> 2 .. 으로 진행.
	try {
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,bbsId);
		result=pstmt.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(con,pstmt,rs);
	}
	return result;
}

//del 삭제.bbsview. delete문 담당 id가 admin이면 view에서 삭제.
public int del(int bbsId) {
	con=getConnect();
	String sql="DELETE FROM BBS WHERE BBSID=?";
	//BBSID=? 삭제.
	try {
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,bbsId);
		result=pstmt.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(con,pstmt,rs);
	}
	return result;
}

//bbsview bbsupdate.
//selectById BBSID=?인 것 select -> 값 get -> constructor bbsDto에 저장.
public BbsDto selectById(String bbsId) {
	BbsDto bbsDto=new BbsDto();
	//BBISID=?인 게시판을  특정하기 위해 객체 생성.위에 있는 LIST와 달리 while(rs.next()){..}는 한번만 돔.BBSID=?으로 게시판 1개만 특정하기때문.
	con=getConnect();
	String sql="SELECT*FROM BBS WHERE BBSID=?";
	try {
		pstmt=con.prepareStatement(sql);
				pstmt.setString(1,bbsId);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					bbsDto.setBbsId(rs.getInt("bbsid"));
					bbsDto.setBbsTitle(rs.getString("bbstitle"));
					bbsDto.setBbsContent(rs.getString("bbscontent"));
					bbsDto.setBbsDate(rs.getTimestamp("bbsdate"));
					bbsDto.setBbsHit(rs.getInt("bbshit"));
					bbsDto.setBbsCategory(rs.getString("bbscategory"));
					bbsDto.setId(rs.getString("id"));
				}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally{
		close(con,pstmt,rs);
	}return bbsDto;
}

public int getCount() {
	con=getConnect();
	String sql="SELECT COUNT(*) FROM BBS";
	try {
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next()) {
				result=rs.getInt(1);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}return result;
}
//dead code

public List<BbsDto> getList(int startRow,int endRow,String select,String search){
List<BbsDto> list=new ArrayList<BbsDto>();
StringBuffer query= new StringBuffer();

try {
	con=getConnect();
	if(select.equals("category")&&search!=null) {
		query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where bbscategory like ?)b) where num between ? and ? order by bbsid desc");
		pstmt=con.prepareStatement(query.toString());
		pstmt.setNString(1, "%"+search+"%");
		pstmt.setInt(2,startRow);
		pstmt.setInt(3,endRow);

		//setNString setString 차이없음.
	}
	else if(select.equals("title")) {
		query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where bbstitle like ?)b) where num between ? and ? order by bbsid desc");
		pstmt=con.prepareStatement(query.toString());
		pstmt.setNString(1, "%"+search+"%");
		pstmt.setInt(2,startRow);
		pstmt.setInt(3,endRow);

	}
	else if(select.equals("content")) {
		query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where bbscontent like ?)b) where num between ? and ? order by bbsid desc");
		pstmt=con.prepareStatement(query.toString());
		pstmt.setNString(1, "%"+search+"%");
		pstmt.setInt(2,startRow);
		pstmt.setInt(3,endRow);

	}
	else if(select.equals("id")) {
		query.append("select*from (select row_number() over (order by bbsid) num,b.* from (select*from bbs where id like ?)b) where num between ? and ? order by bbsid desc");
		pstmt=con.prepareStatement(query.toString());
		pstmt.setNString(1, "%"+search+"%");
		pstmt.setInt(2,startRow);
		pstmt.setInt(3,endRow);

	}
	else {
		query.append("select*from (select row_number() over (order by bbsid) num,b.* from bbs b) where num between ? and ? order by bbsid desc");
		pstmt=con.prepareStatement(query.toString());
		pstmt.setInt(1,startRow);
		pstmt.setInt(2,endRow);
	}
	rs=pstmt.executeQuery();
	
	while(rs.next()) {
		BbsDto bbsDto=new BbsDto();
		bbsDto.setBbsId(rs.getInt("bbsid"));
		bbsDto.setBbsTitle(rs.getString("bbstitle"));
		bbsDto.setBbsContent(rs.getString("bbscontent"));
		bbsDto.setBbsDate(rs.getTimestamp("bbsdate"));
		bbsDto.setBbsHit(rs.getInt("bbshit"));
		bbsDto.setBbsCategory(rs.getString("bbscategory"));
		bbsDto.setId(rs.getString("id"));
		list.add(bbsDto);
	}
}
catch(Exception e) {
	e.printStackTrace();
}finally {
	close(con,pstmt,rs);
}return list;
}

public int getTotalCount( String select,String search) {
	StringBuffer query= new StringBuffer();
	int total=0;
	try {
		con=getConnect();
		if(select.equals("category")&&search!=null) {
			query.append("select count(*)from bbs where bbscategory like ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");


			//setNString setString 차이없음.
		}
		else if(select.equals("title")) {
			query.append("select count(*)from bbs where bbstitle like ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");
			

		}
		else if(select.equals("content")) {
			query.append("select count(*)from bbs where bbscontent like ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");
			

		}
		else if(select.equals("id")) {
			query.append("select count(*)from bbs where id like ?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setNString(1, "%"+search+"%");
			

		}
		else {
			query.append("select count(*)from bbs");
			pstmt=con.prepareStatement(query.toString());
			
		}
		rs=pstmt.executeQuery();
		if(rs.next()) {
			total=rs.getInt(1);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		close(con,pstmt,rs);
	}
	return total;
}
}


