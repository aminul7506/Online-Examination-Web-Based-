<%-- 
    Document   : questionFromPaper
    Created on : Dec 19, 2016, 9:46:09 AM
    Author     : DELL
--%>

<%@page import="model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="QuestionFromPaper.do">
        <h1>Questions</h1>
        <td>
                  <input type="radio" name="choice" value="examQuestion"/>Set as Exam Question
           </td>
           <td>
                  <input type="radio" name="choice" value="practiceQuestion"/>Set as Practice Question
           </td>
        <% 
            
            ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questionForPaper");
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
                    out.println(String.format("<td>%d</td>",ques.getQId()));
                    out.println( String.format("<td>%s</td>", ques.getQuestion()));
                    out.println("</tr>");
                }
                out.println("</table>");
            }
    
%>
  
           <input type="submit" value="Submit"/>          
        </form>
    </body>
</html>
