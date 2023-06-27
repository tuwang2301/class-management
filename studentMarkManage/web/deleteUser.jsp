<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Information</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <%
        User x = (User) request.getSession().getAttribute("currUser");
        if(x == null){
            response.sendRedirect("index.html");
            return;
        }
        StudentDAO sD = new StudentDAO();
        TeacherDAO tD = new TeacherDAO();
        Student curStudent = sD.getStudent(x.getId());
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
        <%
        UserDAO uD = new UserDAO();
        String id = request.getParameter("id");
        User u = uD.getUserByID(id);
        %>    
        <div class="whole-body">
            <h1>Delete an user</h1>
            <form class="info-form" action="deleteUser" method="GET">
                <p>ID: <input type="text" name="id" value="<%= u.getId()%>" readonly /> 
                <p>UserName: <input type="text" name="username" value="<%= u.getUsername() %>" readonly/> 
                <p>Password: <input type="text" name="password" value="<%= u.getPassword() %>"/> 
                <p>Role: 
                    <%if(u.getRole()==1){%>
                    <input type="radio" name="role" checked value="1"> Admin
                    <input type="radio" name="role" value="2" disabled> Teacher
                    <input type="radio" name="role" value="3" disabled> Student
                    <%}%>

                    <%if(u.getRole()==2){%>
                    <input type="radio" name="role" value="1" disabled> Admin
                    <input type="radio" name="role"checked value="2"> Teacher
                    <input type="radio" name="role" value="3" disabled> Student
                    <%}%>

                    <%if(u.getRole()==3){%>
                    <input type="radio" name="role" value="1" disabled> Admin
                    <input type="radio" name="role" value="2" disabled> Teacher
                    <input type="radio" name="role" checked value="3"> Student
                    <%}%>
                <p><input type="submit" value="Confirm to delete"> 
            </form> 
            <p><button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
