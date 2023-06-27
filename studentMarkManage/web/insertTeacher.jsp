<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*"%>
<%@page import = "model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Teacher</title>
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
            <h1> Insert a teacher </h1>
            <form class="info-form" action="insertTeacher" method="POST">
                <p>ID: <input type="text" name="id" value=""  /> 
                <p>Name: <input type="text" name="name" value=""/> 
                <p>Email: <input type="text" name="email" value=""/> 
                <p><input type="submit" value="Insert"> 
            </form>  
            <p><button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
