<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Group</title>
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <h1 align="center">Group: "${group.name}"</h1>
    <h2 align="center">
        Faculty: <a href="faculty?id=${faculty.id}">"${faculty.name}"</a>
    </h2>
    <h2 align="center">
        <a href="students?id=${group.id}">List of students</a>
    </h2>
    <div align="center">
        <form action="groupschedule" method="get">
            <input hidden="true" type="text" name="groupId"
                value="${group.id}" />
            <div align="center">
                <h2 align="center">Schedule from</h2>
                <input name="dayStart" type="text"
                    placeholder="DD.MM.YYYY">
                <h2 align="center">to</h2>
                <input name="dayEnd" type="text"
                    placeholder="DD.MM.YYYY"> <br> <br>
                <input type="submit" value="show">
            </div>
        </form>
    </div>
    <br>
    <div align="center">
        <p>
            <a href="index.jsp">Main</a>
        </p>
    </div>
    <jsp:include page="/_footer.jsp"></jsp:include>
</body>
</html>
