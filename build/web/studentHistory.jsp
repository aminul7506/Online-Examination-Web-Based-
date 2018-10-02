<%-- 
    Document   : studentHistory
    Created on : Dec 15, 2016, 1:49:58 PM
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
            db.getStudentclass(username1);
            out.println("<h1>Class : " + db.getStudentclass(username1) + "<br/></h1>");
            db.getStudentId(username1);
            out.println("<h1>Student Id : " + db.getStudentId(username1) + "<br/></h1>");
            
             //String username1 = (String) session.getAttribute("username");
             //DataAccess db = new DataAccess();
             List<String> details = new ArrayList<String>();
            // DataAccess db = new DataAccess();
             //out.print(String.format("%s %s %s</br>","Student Name : ",name,username1));
             details = db.getStudentSubjectDetails(username1, subject);
             out.println("Set Code Total Marks Achieved Marks");
             for(String Sn : details)
            {
                out.println(Sn);
               // counter++;
            }
            %>
            <form method="post" action="studentHistory">
             <input type="submit" value="Go to Exam History" />
            </form>
    </body>
</html>
