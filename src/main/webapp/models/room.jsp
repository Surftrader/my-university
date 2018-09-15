<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Room</title>
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <h1 align="center">Room: #${room.number}</h1>
    <div align="center">
        <table border="1" cellpadding="7" cellspacing="1">
            <tr>
                <td width="35%" align="center">Room Capacity</td>
                <td width="70%" align="center">Department</td>
            </tr>
            <tr>
                <td width="35%" align="center">${room.capacity}</td>
                <td width="70%" align="center"><a
                    href="department?id=${department.id}">${department.name}</a></td>
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
