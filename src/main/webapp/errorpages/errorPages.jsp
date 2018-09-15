<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.poseal.university.exception.DAOException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/" />
<title>Error page</title>
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <br>
    <h1 align="center">${errorMessage}</h1>
    <br>
    <div align="center">
        <p>
            <a href="index.jsp">Main</a>
        </p>
    </div>
    <jsp:include page="/_footer.jsp"></jsp:include>
</body>
</html>
