<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lesson</title>
</head>
<body>
    <jsp:include page="../_header.jsp"></jsp:include>
    <br>
    <h1 align="center">Lesson</h1>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="6%" align="center">id</td>
                <td width="20%" align="center">time_start</td>
                <td width="20%" align="center">time_end</td>
                <td width="9%" align="center">group</td>
                <td width="9%" align="center">subject</td>
                <td width="20%" align="center">teacher</td>
                <td width="6%" align="center">room</td>
            </tr>
            <tr>
                <td align="center">${lesson.id}</td>
                <td><javatime:format value="${lesson.timeStart}"
                        style="MS" /></td>
                <td><javatime:format value="${lesson.timeEnd}"
                        style="MS" /></td>
                <td>${lesson.group.name}</td>
                <td>${lesson.subject.name}</td>
                <td>${lesson.teacher.name}
                    ${lesson.teacher.surname}</td>
                <td>${lesson.room.number}</td>
            </tr>
        </table>
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
