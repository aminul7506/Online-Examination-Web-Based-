<%-- 
    Document   : teacherInterface
    Created on : Dec 13, 2016, 4:44:00 AM
    Author     : DELL
--%>

<%@page import="model.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="teaIntServlet.do">
        <%--<form method="post" action="teaIntServlet.do">
            <input type="submit" value="Show Statement"/>          
        </form>
         <h1>Teacher Interface</h1>
          <a href="mcq.jsp"> MCQ Upload</a>
          <br>
          <a href="questionPaper.jsp">Question Paper Upload</a>
           <br>
           <a href="examQues.jsp">Exam Question Upload</a>--%>
           <h1>Teacher Interface</h1>
           <h2>Your  Class and Subject </h2>
        <% 
            
            ArrayList<Subject> subjects = (ArrayList<Subject>) session.getAttribute("subjects");
            if(subjects==null)
            {
                out.println("");
            }
            else if(subjects.size()==0)
            {
                out.println("no  records.");
            }
            else{
                out.println("<table>");
                out.println("<hr><td>SUBJECT </td><td>CLASS</td></hr>");
   
                for(Subject sub: subjects)
                {
                    
                    out.println("<tr>");
                    out.println(String.format("<td><input type=radio name=subject value=%s /> %s</td>",sub.getSubId(),sub.getSubId()));
                    out.println( String.format("<td>%d</td>", sub.getClassId()));
                    out.println("</tr>");
                }
                out.println("</table>");
            }
    
%>
          <h3>Choose Option</h3>
          <br>
           <td>
                  <input type="radio" name="option" value="MCQ Upload"/>MCQ Upload
           </td>
           
           <td>
                  <input type="radio" name="option" value="Descriptive Question Upload"/>Descriptive Question Upload
           </td>
           <td>
                  <input type="radio" name="option" value="Question Paper Upload"/>Question Paper Upload
           </td>
           
            <td>
                  <input type="radio" name="option" value="Available QuestionPaper"/>Available Question Paper
           </td>     

           
           
          <td>
                  <input type="radio" name="option" value="Answer Check"/>Answer check
           </td>
           
           <br>
           <br>
            <input type="submit" value="Submit"/>          
        </form>
           
           <%--
          <a href="mcq.jsp"> MCQ Upload</a>
          <br>
          <a href="questionPaper.jsp">Question Paper Upload</a>
           <br>
           <a href="examQues.jsp">Exam Question Upload</a> --%>
   
           
           
    </body>
</html>
