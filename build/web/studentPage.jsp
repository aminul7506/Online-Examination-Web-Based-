<%-- 
    Document   : studentpage
    Created on : Dec 12, 2016, 11:54:29 PM
    Author     : Sojal
--%>

<%@page import="servlet.loginProcess"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DB.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <h1>Student Page</h1>
        
       
    </head>
    <body>
         <%
            String username = (String) session.getAttribute("username");
            if(username==null)
            {
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);
            }
        %>
        
        
        <% 
             String username1 = (String) session.getAttribute("username");
             DataAccess db = new DataAccess();
             List<String> subjectName = new ArrayList<String>();
             
             int counter = 1;
             Integer class1 = db.getStudentclass(username1);
             //out.print(String.format("%s %s %s</br>","Student Name : ",name,username1));
             subjectName = db.getSubject(class1);
             
             out.println(String.format("<h1><pre>%s     %s     %s</pre></h1>","Class","Subject","t_id" ));
             for(String Sn : subjectName)
            {
                out.print(String.format("<h1>%s</h1>",Sn ));
                out.print("\n");
                counter++;
            }
            %>
             <form method="post" action="studentPage">
                 <h1>Please enter subject according to subject code carefully and then go to examination </h1>
             Enter your subject: <input type="text" name="subject" /> <br/>
             <input type="radio" name="type" value="practiceQuestion" /> Practice Question <br/>
            <input type="radio" name="type" value="examinationQuestion" /> Examination Question <br/>
            <input type="submit" name = "type1" value="Go to Question Paper" />
             <input type="submit" name = "type1" value="My Profile" />
             <input type="submit" name = "type1" value="Log Out" />
              <input type="submit" name = "type1" value="Delete Account" />
             </form>
             </br>
              <%
                 
                 //String subject = request.getParameter("subject");
                 //session.setAttribute("subject", subject);
                 //System.out.println(loginProcess.subject1);
                 %>
      <%--  <a href="practiceQuestion.jsp">Practice Examination</a>
        </br>
        <a href="examQuestion.jsp">Examination Question</a>
       --%>
    </body>
</html>
