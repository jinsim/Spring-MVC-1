<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--JSP가 제공해주는 jstl을 이용하면, 반복문같은 로직들을 태그를 통해 깔끔하게 해결 가능하다.--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <tbody>
<%--  <%--%>
<%--    for (Member member : members) {--%>
<%--      out.write("<tr>\n");--%>
<%--      out.write("  <td>" + member.getId() + "</td>\n");--%>
<%--      out.write("  <td>" + member.getUsername() + "</td>\n");--%>
<%--      out.write("  <td>" + member.getAge() + "</td>\n");--%>
<%--      out.write("</tr>\n");--%>
<%--    }--%>
<%--  %>--%>
<%--  items에는 request에 넣은 키값을 적는다.--%>
  <c:forEach var="item" items="${members}">
    <tr>
      <td>${item.id}</td>
      <td>${item.username}</td>
      <td>${item.age}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>