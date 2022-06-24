package kr.ac.kopo.ctc.kopo27.dao;

import java.sql.SQLException;
import java.util.List;

import kr.ac.kopo.ctc.kopo27.domain.BoardItem;

public interface BoardItemDao {
	// CRUD
	// ctrl shift o 자동임포트
	BoardItem create(BoardItem boardItem) throws SQLException, ClassNotFoundException;
	BoardItem selectOne(int id) throws ClassNotFoundException, SQLException;
	List<BoardItem> selectAll(int page, int countPerPage) throws ClassNotFoundException, SQLException;
	BoardItem update(BoardItem boardItem) throws ClassNotFoundException, SQLException;
	BoardItem delete(int id) throws ClassNotFoundException, SQLException;
	void deleteAll() throws ClassNotFoundException, SQLException;
	int count() throws ClassNotFoundException, SQLException;
}
