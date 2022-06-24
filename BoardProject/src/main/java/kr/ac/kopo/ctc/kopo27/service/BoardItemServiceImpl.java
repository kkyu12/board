package kr.ac.kopo.ctc.kopo27.service;

import java.sql.SQLException;
import java.util.List;

import kr.ac.kopo.ctc.kopo27.dao.BoardItemDao;
import kr.ac.kopo.ctc.kopo27.dao.BoardItemDaoImpl;
import kr.ac.kopo.ctc.kopo27.domain.BoardItem;
import kr.ac.kopo.ctc.kopo27.service.dto.Pagination;

public class BoardItemServiceImpl implements BoardItemService {

	private BoardItemDao boardItemDao = new BoardItemDaoImpl();

	@Override
	public Pagination getPagination(int c, int pageSize, int countPerPage, int totalCount) {
		// 현재페이지, 출력될 페이지개수, 페이지의 자료개수, 총 자료개수
		Pagination pagination = new Pagination();
		int totalPage, start, end, p, n;
		// 총페이지 계산
		totalPage = (int) Math.ceil(totalCount / (double) countPerPage);
		if (totalPage != Math.ceil(totalCount / (double) countPerPage)) {
			totalPage++;
		}
		// 현재페이지에 불가능한 값을 넣었을 경우 처리
		if (c > totalPage) {
			c = totalPage;
		}
		if (c < 1) {
			c = 1;
		}
		// 출력되는 페이지의 시작값과 끝값
		// start = c - (c % pageSize) + 1;
		// int startPage = (cPage / pageSize) * pageSize + 1;	
		// if ((cPage % pageSize) == 0) {		
		// 	startPage -= pageSize;
		// }
		if (c % pageSize == 0) {
			start = ((c - 1) / pageSize) * pageSize + 1;
		} else {
			start = (c / pageSize) * pageSize + 1;
		}
		end = start + pageSize - 1;
		// 만약 끝 값이 총 페이지보다 크다면 총페이지만 나오도록
		if (end > totalPage) {
			end = totalPage;
		}
		// 이전 페이지열 계산
		if (start == 1) {
			p = 1;
		} else {
			p = start - 1;
		}
		// 다음 페이지열 계산
		if (end == totalPage) {
			n = totalPage;
		} else {
			n = end + 1;
		}

		pagination.setPp(1); // 제일 첫 페이지로 이동
		pagination.setP(p); // 이전 페이지열로 이동
		pagination.setN(n); // 뒷 페이지열로 이동
		pagination.setNn(totalPage); // 제일 마지막 페이지로 이동
		pagination.setC(c); // 출력할 페이지 = 현재 페이지
		pagination.setStart(start); // 페이지 열의 시작
		pagination.setEnd(end); // 페이지 열의 마지막

		return pagination;
	}

	@Override
	public BoardItem create(BoardItem boardItem) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return boardItemDao.create(boardItem);
	}

	@Override
	public BoardItem selectOne(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return boardItemDao.selectOne(id);
	}

	@Override
	public List<BoardItem> selectAll(int page, int countPerPage) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return boardItemDao.selectAll(page, countPerPage);
	}

	@Override
	public BoardItem update(BoardItem boardItem) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return boardItemDao.update(boardItem);
	}

	@Override
	public BoardItem delete(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return boardItemDao.delete(id);
	}

	@Override
	public int count() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return boardItemDao.count();
	}

}
