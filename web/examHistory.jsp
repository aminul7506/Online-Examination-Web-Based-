<%-- 
    Document   : examHistory
    Created on : Dec 15, 2016, 3:55:17 PM
    Author     : Sojal
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DB.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String subject = (String) session.getAttribute("subject");
            out.println("<h1>Student Details about " + subject + "<br/></h1>");
            String username1 = (String) session.getAttribute("username");
            out.println("<h1>Username : " + username1 + "<br/></h1>");
            DataAccess db = new DataAccess();
            //db.getStudentclass(username1);
            out.println("<h1>Class : " + db.getStudentclass(username1) + "<br/></h1>");
            db.getStudentId(username1);
            out.println("<h1>Student Id : " + db.getStudentId(username1) + "<br/></h1>");
            List <String>details = new ArrayList<String>(); 
            out.println("<h1> MCQ Details </h1><br/>");
            details = db.getAllDetailsofaSubjectforaStudent(username1, subject);
             for(String Sn : details)
            {
                out.println(Sn + "<br/>");
            }
             out.println("<h1> Descriptive Details </h1><br/>");
             details = db.getAllDetailsofaSubjectforaStudentDesc(username1, subject);
             for(String Sn : details)
            {
                out.println(Sn + "<br/>");
            }
        %>
    </body>
</html>
