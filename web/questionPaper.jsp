<%-- 
    Document   : questionPaper
    Created on : Dec 13, 2016, 11:41:26 AM
    Author     : DELL
--%>

<%@page import="model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Subject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="QuestionPaperServlet.do">
             Duration<input type="text" name="duration" /><br>
            Total Marks <input type="text" name="totalMarks" /> <br/>
        
        <h1>Choose Question for Question Paper</h1>
        <% 
            
            ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
            if(questions==null)
            {
                out.println("");
            }
            else if(questions.size()==0)
            {
                out.println("no  records.");
            }
            else{
                out.println("<table>");
                out.println("<hr><td>Question ID </td><td> Question</td></hr>");
   
                for(Question ques: questions)
                {
                    
                    out.println("<tr>");
                    out.println(String.format("<td><input type=checkbox name=%d value=%d /> %d</td>",ques.getQId(),ques.getQId(),ques.getQId()));
                    out.println( String.format("<td>%s</td>", ques.getQuestion()));
                    out.println("</tr>");
                }
                out.println("</table>");
            }
    
%>
     <br>
            <input type="submit" value="Submit"/>
    </form>
    </body>
</html>
