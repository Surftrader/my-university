<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teacher</title>
</head>
<body>
    <jsp:include page="../_header.jsp"></jsp:include>
    <h1 align="center">Teacher: ${teacher.name} ${teacher.surname}</h1>
    <h2 align="center">
        Department: <a href="department?id=${department.id}">"${department.name}"</a>
    </h2>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="50%" align="center">Subjects</td>
            </tr>
            <tr>
                <td><a href="subject?id=${subject.id}">${subject.name}</a></td>
            </tr>
        </table>
        <br>
    </div>
    <div align="center">
        <form action="teacherschedule" method="get">
            <input hidden="true" type="text" name="teacherId"
                value="${teacher.id}" />
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
    <jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>
