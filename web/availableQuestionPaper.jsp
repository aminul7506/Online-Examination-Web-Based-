<%-- 
    Document   : availableQuestionPaper
    Created on : Dec 19, 2016, 8:46:00 AM
    Author     : DELL
--%>

<%@page import="model.QuestionPaper"%>
<%@page import="model.QuestionPaper"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Question Papers</h1>
        <form method="post" action="availableQuestionPaper.do">
        <% 
            
            ArrayList<QuestionPaper> questionPapers = (ArrayList<QuestionPaper>) session.getAttribute("questionPapers");
            if(questionPapers==null)
            {
                out.println("");
            }
            else if(questionPapers.size()==0)
            {
                out.println("no  records.");
            }
            else{
                out.println("<table>");
                out.println("<hr><td>Question No </td><td> Duration</td><td>Full Marks</td></hr>");
   
                for(QuestionPaper ques: questionPapers)
                {
                    
                    out.println("<tr>");
                    out.println(String.format("<td><input type=radio name=qNo value=%d /> %d</td>",ques.getQNo(),ques.getQNo()));
                    out.println( String.format("<td>%d</td>", ques.getDuration()));
                    out.println( String.format("<td>%d</td>", ques.getTotalMarks()));
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
