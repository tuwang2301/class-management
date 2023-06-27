<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Student</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <%!String method;%>
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
                    case 1:method="get";%> 

                <span>Hello Admin</span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proAdmin.jsp"'>Edit</div>

                <%break;
                    case 2:method="post";%>     

                <span>Hello <%=curTeacher.getName()%></span>
                <div class="home" onclick='window.location.href = "listStudent.jsp"'>Home</div>
                <div class="my-profile" onclick='window.location.href = "proTeacher.jsp"'>My Profile</div>

                <%break;
                    case 3:method="post";%> 

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
         String id = request.getParameter("id");
         if(id == null || id.trim().isEmpty()){
           return;
         }
         StudentDAO sD = new StudentDAO();
         Student s = sD.getStudent(id);
        %>    
        <div class="whole-body">
            <h1> Update a student </h1>
            <form class="info-form" action="updateStudent" method="POST">
                <label>ID:</label>
                <input type="text" name="id" value="<%= s.getId()%>" readonly /> 
                <p><label>Name:</label>
                    <input type="text" name="name" value="<%= s.getName() %>"/> 
                <p><label>Email:</label>
                    <input type="text" name="email" value="<%= s.getEmail() %>"/> 
                <p><label>Date of birth:</label>
                    <input type="date" name="dob" value="<%= s.getDob() %>"/> 
                <p><label>Gender:</label>
                    <%if(s.getGender()){%>
                    Male <input type="radio" name="gender" checked value="true">
                    Female <input type="radio" name="gender" value="false">
                    <%}else{%>
                    Male <input type="radio" name="gender" value="true">
                    Female <input type="radio" name="gender" checked value="false">
                    <%}%>
                <p><input type="submit" value="Update"> 
            </form>  
            <p><button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
