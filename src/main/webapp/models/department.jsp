<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Department</title>
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <h1 align="center">Department: "${department.name}"</h1>
    <h2 align="center">
        Faculty: <a href="faculty?id=${faculty.id}">"${faculty.name}"</a>
    </h2>
    <br>
    <div align="center">
        <h2>List of teachers</h2>
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="50%" align="center">Teachers</td>
            </tr>
            <tr>
                <c:forEach items="${listTeachers}" var="teacher">
                    <tr>
                        <td width="50%" align="left"><a
                            href="teacher?id=${teacher.id}"><c:out
                                    value="${teacher.name} ${teacher.surname}" /></a></td>
                    </tr>
                </c:forEach>
            </tr>
        </table>
    </div>
    <div align="center">
        <h2>List of rooms</h2>
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="50%" align="center">Number</td>
                <td width="50%" align="center">Capacity</td>
            </tr>
            <tr>
                <c:forEach items="${listRooms}" var="room">
                    <tr>
                        <td width="50%" align="center"><a
                            href="room?id=${room.id}"><c:out
                                    value="${room.number}" /></a></td>
                        <td width="50%" align="center"><c:out
                                value="${room.capacity}" /></td>
                    </tr>
                </c:forEach>
            </tr>
        </table>
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
