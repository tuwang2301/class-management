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
        <title>Student Profile</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <%
        User x = (User) request.getSession().getAttribute("currUser");
        if(x == null || x.getRole() != 3){
            response.sendRedirect("index.html");
            return;
        }
        StudentDAO sD = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        Student curStudent = sD.getStudent(x.getId());
        Teacher curTeacher = tD.getTeacher(x.getId());
        Student s = sD.getStudent(x.getId());
        MarkDAO mD = new MarkDAO();
        List<Mark> marks = mD.getMarksByStID(s.getId());
    %>
    <body>
        <header>
            <div class="header-left">
                <%switch (x.getRole()){
                    case 1:%> 

                <span>Hello Admin</span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proAdmin.jsp"'>Edit</div>

                <%break;
                    case 2:%>     

                <span>Hello <%=curTeacher.getName()%></span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proTeacher.jsp"'>My Profile</div>

                <%break;
                    case 3:%> 

                <span>Hello <%=curStudent.getName()%></span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proStudent.jsp"'>My Profile</div>

                <%break;}%>
            </div>
            <div class="header-right">
                <button class="btn-logout" onclick='window.location.href = "logout"'>Logout</button>
            </div>
        </header>
        <div class="header-placeholder"></div>
        <div class="whole-body">
            <p><h1>Your Information</h1></p>
        <button onclick='window.location.href = "<%="updateStudent.jsp?id="%><%=s.getId()%>"'>Edit Profile</button>
        <!-- Information table -->
        <table border=1>
            <tr>
                <th colspan="2">Information</th>
            </tr>
            <tr>
                <td>ID</td>
                <td><%=s.getId()%></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><%=s.getName()%></td>
            </tr>
            <tr>
                <td>Date of birth</td>
                <td><%=s.getDob()%></td>
            </tr>
            <tr>
                <td>Gender</td>
                <%if(s.getGender()){%>
                <td>Male</td>
                <%}else{%>
                <td>Female</td>
                <%}%>
            </tr>
            <tr>
                <td>Email</td>
                <td><%=s.getEmail()%></td>
            </tr>
        </table>
        <p><h1>Your Mark</h1></p>
    <!-- Mark Table -->
    <table border="1">
        <tr>
            <th>Subject ID</th>
            <th>GPA</th>
        </tr>
        <%for (Mark mark : marks) {
        %>
        <tr>
            <td><%=mark.getSubject().getId()%></td>
            <td><%=mark.getGpa()%></td>
        </tr>
        <%}
        %>
    </table>   
    <button onclick='window.history.go(-1);'>Back to previous page</button>
</div>
</body>
</html>
