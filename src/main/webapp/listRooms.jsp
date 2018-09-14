<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of rooms</title>
</head>
<body>
    <h1 align="center">List of rooms</h1>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="5%" align="center">ID</td>
                <td width="50%" align="center">Room Number</td>
                <td width="50%" align="center">Room Capacity</td>
            </tr>
            <tr>
                <c:forEach items="${listRooms}" var="room">
                    <tr>
                        <td width="5%" align="center"><c:out
                                value="#${room.id}" /></td>
                        <td width="50%" align="left"><a
                            href="room?id=${room.id}"><c:out
                                    value="${room.number}" /></a></td>
                        <td width="50%" align="left"><c:out
                                value="${room.capacity}" /></td>
                    </tr>
                </c:forEach>
            </tr>
        </table>
        <br>
    </div>
    <div align="center">
        <p>
            <a href="index.jsp">Main</a>
        </p>
    </div>
    <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
