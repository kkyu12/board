package kr.ac.kopo.ctc.kopo27.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import kr.ac.kopo.ctc.kopo27.domain.BoardItem;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardItemDaoTest {
	private static BoardItemDao boardItemDao = new BoardItemDaoImpl();
	private BoardItem boardItem1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		BoardItem boardItem1 = new BoardItem();
		boardItem1.setTitle("title1");
		boardItem1.setContent("content1");
		boardItem1.setAuthor("author1");
		boardItemDao.create(boardItem1);
		
		BoardItem boardItem2 = new BoardItem();
		boardItem2.setTitle("title2");
		boardItem2.setContent("content2");
		boardItem2.setAuthor("author2");
		boardItemDao.create(boardItem2);
		
		BoardItem boardItem3 = new BoardItem();
		boardItem3.setTitle("title3");
		boardItem3.setContent("content3");
		boardItem3.setAuthor("author3");
		boardItemDao.create(boardItem3);
		
		BoardItem boardItem4 = new BoardItem();
		boardItem4.setTitle("title4");
		boardItem4.setContent("content4");
		boardItem4.setAuthor("author4");
		boardItemDao.create(boardItem4);
		
		BoardItem boardItem5 = new BoardItem();
		boardItem5.setTitle("title5");
		boardItem5.setContent("content5");
		boardItem5.setAuthor("author5");
		boardItemDao.create(boardItem5);
	}
	/*
	@BeforeEach
	void setUp() throws Exception {
		boardItemDao.deleteAll();
		
		BoardItem boardItem1 = new BoardItem();
		boardItem1.setTitle("title1");
		boardItem1.setContent("content");
		boardItem1.setAuthor("author1");
		boardItemDao.create(boardItem1);
		
		BoardItem boardItem2 = new BoardItem();
		boardItem2.setTitle("title2");
		boardItem2.setContent("content");
		boardItem2.setAuthor("author2");
		boardItemDao.create(boardItem2);
		
		BoardItem boardItem3 = new BoardItem();
		boardItem3.setTitle("title2");
		boardItem3.setContent("content3");
		boardItem3.setAuthor("author3");
		boardItemDao.create(boardItem3);
		
		BoardItem boardItem4 = new BoardItem();
		boardItem4.setTitle("title4");
		boardItem4.setContent("content4");
		boardItem4.setAuthor("author4");
		boardItemDao.create(boardItem2);
		
		BoardItem boardItem5 = new BoardItem();
		boardItem5.setTitle("title5");
		boardItem5.setContent("content5");
		boardItem5.setAuthor("author5");
		boardItemDao.create(boardItem5);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	*/
	@Test
	@Order(1)
	void count1() throws ClassNotFoundException, SQLException {
		int count = boardItemDao.count();
		assertEquals(count, 5);
	}
	
	@Test
	@Order(2)
	void select1() throws ClassNotFoundException, SQLException {
		BoardItem boardItem = boardItemDao.selectOne(1);
		assertEquals(boardItem.getId(), 1);
		assertEquals(boardItem.getTitle(), "title1");
	}
	
	@Test
	@Order(3)
	void create1() throws ClassNotFoundException, SQLException {
		BoardItem boardItem = new BoardItem();
		boardItem.setTitle("title6");
		boardItem.setContent("content6");
		boardItem.setAuthor("author6");
		BoardItem boardItem1 = boardItemDao.create(boardItem);
		assertEquals(boardItem1.getTitle(), "title6");
		assertEquals(boardItem1.getContent(), "content6");
		assertEquals(boardItem1.getAuthor(), "author6");
	}
	
	@Test
	@Order(4)
	void delete1() throws ClassNotFoundException, SQLException {
		boardItemDao.delete(6);
		int count = boardItemDao.count();
		assertEquals(count, 5);
	}
	
	@Test
	@Order(5)
	void selectAll() throws ClassNotFoundException, SQLException {
		List<BoardItem> boardItems = boardItemDao.selectAll(1, 5);
		assertEquals(boardItems.get(0).getId(), 1);
		assertEquals(boardItems.get(0).getTitle(), "title1");
		assertEquals(boardItems.get(1).getId(), 2);
		assertEquals(boardItems.get(1).getTitle(), "title2");
		assertEquals(boardItems.get(2).getId(), 3);
		assertEquals(boardItems.get(2).getTitle(), "title3");
		assertEquals(boardItems.get(3).getId(), 4);
		assertEquals(boardItems.get(3).getTitle(), "title4");
		assertEquals(boardItems.get(4).getId(), 5);
		assertEquals(boardItems.get(4).getTitle(), "title5");
	}
	
	@Test
	@Order(6)
	void update() throws ClassNotFoundException, SQLException {
		BoardItem boardItem = new BoardItem();
		boardItem.setId(2);
		boardItem.setTitle("title22");
		BoardItem boardItem1 = boardItemDao.update(boardItem);
		//boardItemDao.update(boardItem);
		//BoardItem boardItem1 = boardItemDao.selectOne(2);
		assertEquals(boardItem1.getId(), 2);
		assertEquals(boardItem1.getTitle(), "title22");
	}

	@Test
	@Order(7)
	void deleteAll() throws ClassNotFoundException, SQLException {
		boardItemDao.deleteAll();
		int count = boardItemDao.count();
		assertEquals(count, 0);
	}
}
