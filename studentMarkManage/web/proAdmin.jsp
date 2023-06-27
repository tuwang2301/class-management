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
        <title>ADMIN Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <%
        User x = (User) request.getSession().getAttribute("currUser");
        if(x == null){
            response.sendRedirect("index.html");
            return;
        }
        StudentDAO u = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        Student curStudent = u.getStudent(x.getId());
        Teacher curTeacher = tD.getTeacher(x.getId());
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
            <div><h1>List of options</h1></div>
            <div id="options">
                <div class="option" onclick="window.location.href = 'listStudent_1.jsp'">
                    <h2>Edit List of Students</h2>
                    <p>Insert, Update, Delete</p>
                </div>
                <div class="option" onclick="window.location.href = 'listSubject.jsp'">
                    <h2>Edit List of Subjects</h2>
                    <p>Insert, Update, Delete</p>
                </div>
                <div class="option" onclick="window.location.href = 'listTeacher.jsp'">
                    <h2>Edit List of Teacher</h2>
                    <p>Insert, Update, Delete</p>
                </div>
                <div class="option" onclick="window.location.href = 'listMark.jsp'">
                    <h2>Edit List of Mark</h2>
                    <p>Update</p>
                </div>
                <div class="option" onclick="window.location.href = 'listUser.jsp'">
                    <h2>Edit List of Users</h2>
                    <p>Insert, Update, Delete</p>
                </div>
            </div>
            <button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
