<%-- 
    Document   : practiceExamSet
    Created on : Dec 18, 2016, 7:41:52 PM
    Author     : Sojal
--%>

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
            //out.println("<h1>Student Details about " + subject + "<br/></h1>");
            String username1 = (String) session.getAttribute("username");
            out.println("<h1>Username : " + username1 + "<br/></h1>");
            DataAccess db = new DataAccess();
            //db.getStudentclass(username1);
            out.println("<h1>Class : " + db.getStudentclass(username1) + "<br/></h1>");
            db.getStudentId(username1);
            out.println("<h1>Student Id : " + db.getStudentId(username1) + "<br/></h1>");
            out.println("<Question Set available for you to practice yet.</h1>");
            ArrayList<Integer> q_no = new ArrayList<Integer>();
            q_no = db.PgetQ_no(subject);
            if(q_no.size() == 0) out.println("<h1> No Question available for practice.</h1>");
            else{
             for(Integer Sn : q_no)
                {
                out.println("Question Set : " + Sn + "<br/>");
                //counter++;
                }
            }
            %>
            
             <form method="post" action="practiceExamSet">
                 <h1>Please enter set code carefully and then go to examination </h1>
             Enter Available Set Code: <input type="text" name="set" /> <br/>
             <input type="submit" value="Go to Practice Exam" />
    </body>
</html>
