<%-- 
    Document   : answerCheck
    Created on : Dec 20, 2016, 7:38:56 PM
    Author     : DELL
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form method="post" action="AnswerCheck.do">
       
        <% 
            
            ArrayList<Integer> students = (ArrayList<Integer>) session.getAttribute("students");
            if(students==null)
            {
                out.println("");
            }
            else if(students.size()==0)
            {
                out.println("no  records.");
            }
            else{
                out.println("<table>");
                out.println("<hr><td>STUDENT ID </td></hr>");
   
                for(Integer stu: students)
                {
                    
                    out.println("<tr>");
                    out.println(String.format("<td><input type=radio name=student value=%d /> %d</td>",stu,stu));
   
                    out.println("</tr>");
                }
                out.println("</table>");
            }
    
%>
        
        
        <input type="submit" value="Submit"/>  
       </form>
    </body>
</html>
