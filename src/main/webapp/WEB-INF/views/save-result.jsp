<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
성공
<ul>
<%--  원래 이런 식으로 가져와야 한다. 그러나 JSP가 제공해주는 프로퍼티 접근법을 사용하면 아래와 같이 편리하게 조회 가능하다.--%>
<%--  <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--  <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--  <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>--%>
  <li>id=${member.id}</li>
  <li>username=${member.username}</li>
  <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>