<%@page import="kr.ac.kopo.ctc.kopo27.domain.BoardItem"%>
<%@page import="java.util.List"%>
<%@page import="kr.ac.kopo.ctc.kopo27.dao.BoardItemDao"%>
<%@page import="kr.ac.kopo.ctc.kopo27.dao.BoardItemDaoImpl"%>
<%@page import="kr.ac.kopo.ctc.kopo27.service.dto.Pagination"%>
<%@page import="kr.ac.kopo.ctc.kopo27.service.BoardItemService"%>
<%@page import="kr.ac.kopo.ctc.kopo27.service.BoardItemServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	
	BoardItemService boardItemService = new BoardItemServiceImpl();

	String cPage = request.getParameter("page");
    if (cPage == null || "null".equals(cPage)) {
        cPage = "1";
      }
    int cPageInt = Integer.parseInt(cPage);
	int totalCount = boardItemService.count();

	Pagination pagination = boardItemService.getPagination(cPageInt, 15, 50, totalCount);
	pageContext.setAttribute("pagination", pagination);
	
	BoardItemDao boardItemDao = new BoardItemDaoImpl();
	List<BoardItem> boardItems = boardItemDao.selectAll(cPageInt, 50);
	pageContext.setAttribute("boardItems", boardItems);
%>
<h1>폴리텍 게시판</h1>
  <table width="650" border="1" cellspacing="0" cellpadding="5">
    <tr>
      <td>번호</td>
      <td>제목</td>
      <td>날짜</td>
      <td>내용</td>
      <td>조회수</td>
      <td>글쓴이</td>
    </tr>
    <c:forEach var="item" items="${boardItems}">
      <tr>
        <td>${item.id}</td>
        <td>${item.title}</td>
        <td>${item.created}</td>
        <td>${item.content}</td>
        <td>${item.views}</td>
        <td>${item.author}</td>
      </tr>
    </c:forEach>
  </table>
  <table width="650" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td width = 600 align = center><b><a href = 'index.jsp?page=${pagination.p}'> &lt&lt </a></b>
        <c:forEach var="i" begin="${pagination.start}" end="${pagination.end}">
          <b><a href = 'index.jsp?page=${i}'>${i}</a></b>
        </c:forEach>
      <b><a href = 'index.jsp?page=${pagination.n}'> &gt&gt </a></b>
    </tr>
  </table>
</body>
</html>