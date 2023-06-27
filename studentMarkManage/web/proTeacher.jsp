<%-- 
    Document   : studentPro
    Created on : Mar 1, 2023, 10:40:21 AM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*"%>
<%@page import = "model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher Profile</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <%
        User x = (User) request.getSession().getAttribute("currUser");
        if(x == null || x.getRole() != 2){
            response.sendRedirect("index.html");
            return;
        }
        StudentDAO stD = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        Student curStudent = stD.getStudent(x.getId());
        Teacher curTeacher = tD.getTeacher(x.getId());
        Teacher t = tD.getTeacher(x.getId());
        SubjectDAO sD = new SubjectDAO();
        Subject s = sD.getSubjectByTeacher(t.getId());
        MarkDAO mD = new MarkDAO();
        List<Mark> marks = mD.getMarksBySID(s.getId());
    %>
    <body>
        <%@include file="header.jsp" %>
        <div class="header-placeholder"></div>
        <div class="whole-body">
            <p><h1>Your Information</h1></p>
        <button onclick='window.location.href = "<%="updateTeacher.jsp?id="%><%=t.getId()%>"'>Edit Profile</button>
        <table border=1>
            <tr>
                <th colspan="2">Information</th>
            </tr>
            <tr>
                <td>ID</td>
                <td><%=t.getId()%></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><%=t.getName()%></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><%=t.getEmail()%></td>
            </tr>
        </table>

        <!-- Marks of class -->
        <p><h1>Marks of <%=s.getId()%> in SE1724</h1></p>
    <table>
        <tr>
            <th>Student ID</th>
            <th>GPA</th>
            <th></th>
        </tr>
        <%for (Mark mark : marks) {
        %>
        <tr>
            <td><%=mark.getStudent().getId()%></td>
            <td><%=mark.getGpa()%></td>
            <td class='table-option' onclick='window.location.href = "updateMark.jsp?sid=<%=mark.getSubject().getId()%>&stid=<%=mark.getStudent().getId()%>"'>Edit</td>
        </tr>
        <%}
        %>
    </table>   
    <button onclick='window.history.go(-1);'>Back to previous page</button>
</div>
</body>
</html>
