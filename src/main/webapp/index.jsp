<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/" />
<title>University</title>
</head>
<body>
    <jsp:include page="/_header.jsp"></jsp:include>
    <br>
    <br>
    <div align="center">
        <table align="center">
            <tr>
                <td width="50%" align="left"><a href="faculties"><b>Show
                            List of faculties</b></a></td>
                <td width="50%" align="left">
                    <form>
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td width="50%" align="left"><a href="departments"><b>Show
                            List of departments</b></a></td>
                <td width="50%" align="left">
                    <form>
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td width="50%" align="left"><a href="groups"><b>Show
                            List of groups</b></a></td>
                <td width="50%" align="left">
                    <form>
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td width="50%" align="left"><a href="subjects"><b>Show
                            List of subjects</b></a></td>
                <td width="50%" align="left">
                    <form>
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td width="50%" align="left"><a href="rooms"><b>Show
                            List of rooms</b></a></td>
                <td width="50%" align="left">
                    <form>
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td width="50%" align="left"><a href="teachers"><b>Show
                            List of teachers</b></a></td>
                <td width="50%" align="left">
                    <form>
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td width="50%" align="left"><a href="students"><b>Show
                            List of students</b></a></td>
                <td width="50%" align="left">
                    <form action="student/add" method="get">
                        <button type="submit">Add</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td width="50%" align="left"><a href="lessons"><b>Show
                            List of lessons</b></a></td>
                <td width="50%" align="left">
                    <form>
                        <input type="button" value="Add">
                    </form>
                </td>
            </tr>
        </table>
    </div>
    <jsp:include page="/_footer.jsp"></jsp:include>
    <br>
    <br>
</body>
</html>
