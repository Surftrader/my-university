<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student</title>
<base href="${pageContext.request.contextPath}/" />
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <h1 align="center">New (update of) student</h1>
    <h1 align="center">Student: ${student.name} ${student.surname}</h1>
    <div align="center">
        <c:choose>
            <c:when test="${not empty student.id}">
                <form action="student" method="post">
            </c:when>
            <c:otherwise>
                <form action="students" method="post">
            </c:otherwise>
        </c:choose>
        <table>
            <tr>
                <td>ID:</td>
                <td><input hidden="true" name="studentId"
                    type="text" value="${student.id}">${student.id}</td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input name="studentName" type="text"
                    value="${student.name}"></td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td><input name="studentSurname" type="text"
                    value="${student.surname}"></td>
            </tr>
            <tr>
                <td>Group:</td>
                <td><select name=group>
                        <c:choose>
                            <c:when test="${not empty group.name}">
                                <option>${group.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option>Select a group</option>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${listGroups}" var="group">
                            <option value="${group.id}">${group.name}</option>
                        </c:forEach>
                </select></td>
            </tr>
        </table>
        <br>
        <div>
            <button type="submit">Save</button>
        </div>
        </form>
        <br>
        <div align="center">
            <form action="student/delete" method="post">
                <div>
                    <input hidden="true" type="text" name="studentId"
                        value="${student.id}" />
                </div>
                <div>
                    <button type="submit">Delete</button>
                </div>
            </form>
        </div>
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
