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
    <%User x = (User) request.getSession().getAttribute("currUser");
          if(x == null){
              response.sendRedirect("index.html");
              return;
          }
          StudentDAO u = new StudentDAO();
          TeacherDAO tD = new TeacherDAO();
          Student curStudent = u.getStudent(x.getId());
          Teacher curTeacher = tD.getTeacher(x.getId());%>
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
         String sid = request.getParameter("sid");
         String stid = request.getParameter("stid");
         if(sid == null || sid.trim().isEmpty()
           || stid == null || stid.trim().isEmpty()){
           return;
         }
         MarkDAO mD = new MarkDAO();
         Mark m = mD.getMark(sid,stid);
        %>    

        <div class='whole-body'>
            <h1> Update a Mark </h1>
            <form class='info-form' action="updateMark" method=<%=method%>>
                <p>Subject ID: <input type="text" name="sid" value="<%= sid%>" readonly /> 
                <p>Student ID: <input type="text" name="stid" value="<%= stid %>" readonly/> 
                <p>GPA: <input type="text" name="gpa" value="<%= m.getGpa() %>"/> 
                <p><input type="submit" value="Update"> 
            </form>  
            <p><button onclick='window.history.go(-1);'>Back to previous page</button>
        </div>
    </body>
</html>
