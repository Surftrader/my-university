<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of departments</title>
</head>
<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <h1 align="center">List of departments</h1>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="5%" align="center">ID</td>
                <td width="50%" align="center">Department Name</td>
            </tr>
            <tr>
                <c:forEach items="${listDepartments}" var="department">
                    <tr>
                        <td width="5%" align="center"><c:out
                                value="#${department.id}" /></td>
                        <td width="50%" align="left"><a
                            href="department?id=${department.id}"><c:out
                                    value="${department.name}" /></a></td>
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
