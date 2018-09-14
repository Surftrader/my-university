<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student</title>
</head>
<body>
    <jsp:include page="../_header.jsp"></jsp:include>
    <h1 align="center">Student: ${student.name} ${student.surname}</h1>
    <h2 align="center">
        Group: <a href="group?id=${group.id}">${group.name}</a>
    </h2>
    <br>
    <div align="center">
        <p>
            <a href="index.jsp">Main</a>
        </p>
    </div>
    <jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>
