<%-- 
    Document   : answerOfStudent
    Created on : Dec 21, 2016, 6:53:45 AM
    Author     : DELL
--%>

<%@page import="model.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form method="post" action="MarksUpload.do">
       
        <% 
            
            ArrayList<Answer> answers = (ArrayList<Answer>) session.getAttribute("answers");
            if(answers==null)
            {
                out.println("");
            }
            else if(answers.size()==0)
            {
                out.println("no  records.");
            }
            else{
                out.println("<table>");
                //out.println("<hr><td>STUDENT ID </td></hr>");
   
                for(Answer ans: answers)
                {
                    
                    out.println("<tr>");
                    out.println(String.format("<td> %s</td>",ans.getAnswer()));
                    out.println(String.format("<td> <input type=text name=%d /></td>",ans.getQId()));
   
                    out.println("<br></tr>");
                }
                out.println("</table>");
            }
    
%>
        
        
        <input type="submit" value="Submit"/>  
       </form>
    </body>
</html>
