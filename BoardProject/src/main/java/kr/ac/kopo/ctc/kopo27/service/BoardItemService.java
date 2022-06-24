package kr.ac.kopo.ctc.kopo27.service;

import java.sql.SQLException;
import java.util.List;

import kr.ac.kopo.ctc.kopo27.domain.BoardItem;
import kr.ac.kopo.ctc.kopo27.service.dto.Pagination;

public interface BoardItemService {
	// 현재페이지, 출력될 페이지개수, 페이지의 자료개수, 총 자료개수
	Pagination getPagination(int c, int pageSize, int countPerPage, int totalCount);
	BoardItem create(BoardItem boardItem) throws ClassNotFoundException, SQLException;
	BoardItem selectOne(int id) throws ClassNotFoundException, SQLException;
	List<BoardItem> selectAll(int page, int countPerPage) throws ClassNotFoundException, SQLException;
	BoardItem update(BoardItem boardItem) throws ClassNotFoundException, SQLException;
	BoardItem delete(int id) throws ClassNotFoundException, SQLException;
	int count() throws ClassNotFoundException, SQLException;
}
