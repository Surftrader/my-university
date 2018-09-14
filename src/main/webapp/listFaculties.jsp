<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list of faculties</title>
</head>
<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <h1 align="center">List of faculties</h1>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="5%" align="center">ID</td>
                <td width="50%" align="center">Faculty Name</td>
            </tr>
            <c:forEach items="${listFaculties}" var="faculty">
                <tr>
                    <td width="5%" align="center"><c:out
                            value="#${faculty.id}" /></td>
                    <td width="50%" align="left"><a
                        href="faculty?id=${faculty.id}"><c:out
                                value="${faculty.name}" /></a></td>
                </tr>
            </c:forEach>
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
