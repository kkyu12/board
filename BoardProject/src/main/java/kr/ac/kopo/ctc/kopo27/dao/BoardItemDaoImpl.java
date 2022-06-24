package kr.ac.kopo.ctc.kopo27.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.ctc.kopo27.domain.BoardItem;

public class BoardItemDaoImpl implements BoardItemDao {

	@Override
	public BoardItem create(BoardItem boardItem) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스용 jar파일 사용
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopo27", "root", "kopo27"); // database에
																											// 연결
		Statement stmt = conn.createStatement(); // 연결된 데이터베이스에서 자료 가져오기
		String sql = "insert into board(id,title,created,content,views,author) VALUES(null,'" + boardItem.getTitle()
				+ "',date(now()),'" + boardItem.getContent() + "', 0,'" + boardItem.getAuthor() + "');";
		stmt.executeUpdate(sql);
		
		BoardItem boardItem1 = new BoardItem();
		String sql2 = "SELECT * FROM board WHERE id = (SELECT max(id) FROM board);";
		ResultSet rset = stmt.executeQuery(sql2);
		while (rset.next()) {
			boardItem1.setId(rset.getInt(1));
			boardItem1.setTitle(rset.getString(2));
			boardItem1.setCreated(rset.getDate(3));
			boardItem1.setContent(rset.getString(4));
			boardItem1.setViews(rset.getInt(5));
			boardItem1.setAuthor(rset.getString(6));
		}
		rset.close(); // 파일 내용 닫기
		stmt.close(); // 파일 닫기
		conn.close(); // 연결 닫기
		return boardItem1;
	}

	@Override
	public BoardItem selectOne(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스용 jar파일 사용
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopo27", "root", "kopo27"); // database에
																											// 연결
		Statement stmt = conn.createStatement(); // 연결된 데이터베이스에서 자료 가져오기
		BoardItem boardItem = new BoardItem();
		String sql = "select * from board where id=" + id + ";";
		ResultSet rset = stmt.executeQuery(sql);
		while (rset.next()) {
			boardItem.setId(rset.getInt(1));
			boardItem.setTitle(rset.getString(2));
			boardItem.setCreated(rset.getDate(3));
			boardItem.setContent(rset.getString(4));
			boardItem.setViews(rset.getInt(5));
			boardItem.setAuthor(rset.getString(6));
		}
		rset.close(); // 파일 내용 닫기
		stmt.close(); // 파일 닫기
		conn.close(); // 연결 닫기
		return boardItem;
	}

	@Override
	public List<BoardItem> selectAll(int page, int countPerPage) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스용 jar파일 사용
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopo27", "root", "kopo27"); // database에
																											// 연결
		Statement stmt = conn.createStatement(); // 연결된 데이터베이스에서 자료 가져오기

		List<BoardItem> boardItems = new ArrayList<BoardItem>();

		String sql = "select * from board limit " + (countPerPage * (page - 1)) + ", " + countPerPage + ";";
		ResultSet rset = stmt.executeQuery(sql);
		while (rset.next()) {
			BoardItem boardItem = new BoardItem();
			boardItem.setId(rset.getInt(1));
			boardItem.setTitle(rset.getString(2));
			boardItem.setCreated(rset.getDate(3));
			boardItem.setContent(rset.getString(4));
			boardItem.setViews(rset.getInt(5));
			boardItem.setAuthor(rset.getString(6));
			boardItems.add(boardItem);
		}
		rset.close(); // 파일 내용 닫기
		stmt.close(); // 파일 닫기
		conn.close(); // 연결 닫기
		return boardItems;
	}

	@Override
	public BoardItem update(BoardItem boardItem) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스용 jar파일 사용
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopo27", "root", "kopo27"); // database에
																											// 연결
		Statement stmt = conn.createStatement(); // 연결된 데이터베이스에서 자료 가져오기
		stmt.executeUpdate(
				"update board set title ='" + boardItem.getTitle() + "' where id = " + boardItem.getId() + ";");

		BoardItem boardItem1 = new BoardItem();
		String sql = "select * from board where id=" + boardItem.getId() + ";";
		ResultSet rset = stmt.executeQuery(sql);
		while (rset.next()) {
			boardItem1.setId(rset.getInt(1));
			boardItem1.setTitle(rset.getString(2));
			boardItem1.setCreated(rset.getDate(3));
			boardItem1.setContent(rset.getString(4));
			boardItem1.setViews(rset.getInt(5));
			boardItem1.setAuthor(rset.getString(6));
		}
		rset.close(); // 파일 내용 닫기
		stmt.close(); // 파일 닫기
		conn.close(); // 연결 닫기
		return boardItem1;
	}

	@Override
	public BoardItem delete(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스용 jar파일 사용
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopo27", "root", "kopo27"); // database에
																											// 연결
		Statement stmt = conn.createStatement(); // 연결된 데이터베이스에서 자료 가져오기
		stmt.executeUpdate("DELETE FROM board where id =" + id + ";");
		stmt.close(); // 파일 닫기
		conn.close(); // 연결 닫기
		return null;
	}

	@Override
	public int count() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스용 jar파일 사용
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopo27", "root", "kopo27"); // database에
																											// 연결
		Statement stmt = conn.createStatement(); // 연결된 데이터베이스에서 자료 가져오기
		int count = 0;
		ResultSet rset = stmt.executeQuery("select count(*) from board;");
		while (rset.next()) {
			count = rset.getInt(1);
		}
		stmt.close(); // 파일 닫기
		conn.close(); // 연결 닫기
		return count;
	}

	@Override
	public void deleteAll() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // 데이터베이스용 jar파일 사용
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopo27", "root", "kopo27"); // database에
																											// 연결
		Statement stmt = conn.createStatement(); // 연결된 데이터베이스에서 자료 가져오기
		stmt.executeUpdate("DELETE FROM board;");
		stmt.close(); // 파일 닫기
		conn.close(); // 연결 닫기
	}

}
