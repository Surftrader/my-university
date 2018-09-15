<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subject</title>
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <h1 align="center">Subject: "${subject.name}"</h1>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="50%" align="center">Teacher</td>
            </tr>
            <tr>
                <td><a href="teacher?id=${teacher.id}">${teacher.name}
                        ${teacher.surname}</a></td>
            </tr>
        </table>
        <br>
    </div>
    <div align="center">
        <p>
            <a href="index.jsp">Main</a>
        </p>
    </div>
    <jsp:include page="/_footer.jsp"></jsp:include>
</body>
</html>
