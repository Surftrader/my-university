<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teachers schedule</title>
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <h1 align="center">Schedule: "${teacher.name}
        ${teacher.surname}"</h1>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="6%" align="center">id</td>
                <td width="22%" align="center">time_start</td>
                <td width="22%" align="center">time_end</td>
                <td width="20%" align="center">subject</td>
                <td width="20%" align="center">group</td>
                <td width="10%" align="center">room</td>
            </tr>
            <c:forEach items="${listLessons}" var="lesson">
                <tr>
                    <td><a href="lesson?id=${lesson.id}">${lesson.id}</a></td>
                    <td><javatime:format
                            value="${lesson.timeStart}" style="MS" /></td>
                    <td><javatime:format value="${lesson.timeEnd}"
                            style="MS" /></td>
                    <td>${lesson.subject.name}</td>
                    <td>${lesson.group.name}</td>
                    <td>${lesson.room.number}</td>
                </tr>
            </c:forEach>
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
