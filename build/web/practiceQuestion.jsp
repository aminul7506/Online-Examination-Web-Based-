<%-- 
    Document   : practiceQuestion
    Created on : Dec 18, 2016, 8:01:45 PM
    Author     : Sojal
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DB.DataAccess"%>
<%@page import="servlet.studentPage"%>
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
             String username1 = (String) session.getAttribute("username");
             String set = (String) session.getAttribute("set");
             DataAccess db = new DataAccess();
              List<String> question = new ArrayList<String>();
              Integer s_id = db.getStudentId(username1);
            // DataAccess db = new DataAccess();
             //out.print(String.format("%s %s %s</br>","Student Name : ",name,username1));
             question = db.PgetQuestion(subject,Integer.parseInt(set));
            // Integer s_id = db.getStudentId(username1);
             Integer q_no = Integer.parseInt(set);
             boolean res = db.PisQuestionAvailable(s_id,subject,q_no);
             if(res == true){
                 out.println("<h1> You have already practised it. </h1>");
             }
             else{
            
             out.println("<h1> Student Id: " + s_id + ", " + "Question Set : " + q_no + ", " + "Subject Code : "
             + subject + "<br/>" + "Qustion Paper<br/>" + "</h1>");
             int counter = 0;
            // out.println(String.format("%s  %s %s %s</br></br/>","No","Cls","Subject","t_id" ));
            out.println("<h1> MCQ Question</h1>");
             for(String Sn : question)
            {
                out.println(Sn);
                counter++;
            }
             studentPage.question_no = counter;
             }
            %>
            <form method="post" action="practiceQuestion">
             <form>
               01.<input type="radio" name="type1" value="a1" />
               <input type="radio" name="type1" value="b1" />
               <input type="radio" name="type1" value="c1" />
               <input type="radio" name="type1" value="d1" /> <br/>
             
           
               02.<input type="radio" name="type2" value="a2" />
               <input type="radio" name="type2" value="b2" />
               <input type="radio" name="type2" value="c2" />
               <input type="radio" name="type2" value="d2" /> <br/>
            
               03.<input type="radio" name="type3" value="a3" />
               <input type="radio" name="type3" value="b3" />
               <input type="radio" name="type3" value="c3" />
               <input type="radio" name="type3" value="d3" /> <br/>
             
             
               04.<input type="radio" name="type4" value="a4" />
               <input type="radio" name="type4" value="b4" />
               <input type="radio" name="type4" value="c4" />
               <input type="radio" name="type4" value="d4" /> <br/>
             
              
               05.<input type="radio" name="type5" value="a5" />
               <input type="radio" name="type5" value="b5" />
               <input type="radio" name="type5" value="c5" />
               <input type="radio" name="type5" value="d5" /> <br/>
            
               06.<input type="radio" name="type6" value="a6" />
               <input type="radio" name="type6" value="b6" />
               <input type="radio" name="type6" value="c6" />
               <input type="radio" name="type6" value="d6" /> <br/>
             
               07.<input type="radio" name="type7" value="a7" />
               <input type="radio" name="type7" value="b7" />
               <input type="radio" name="type7" value="c7" />
               <input type="radio" name="type7" value="d7" /> <br/>
            
               08.<input type="radio" name="type8" value="a8" />
               <input type="radio" name="type8" value="b8" />
               <input type="radio" name="type8" value="c8" />
               <input type="radio" name="type8" value="d8" /> <br/>
            
               09.<input type="radio" name="type9" value="a9" />
               <input type="radio" name="type9" value="b9" />
               <input type="radio" name="type9" value="c9" />
               <input type="radio" name="type9" value="d9" /> <br/>
             
               10.<input type="radio" name="type10" value="a10" />
               <input type="radio" name="type10" value="b10" />
               <input type="radio" name="type10" value="c10" />
               <input type="radio" name="type10" value="d10" /> <br/><br/><br/>
               <%
                   if(res == false){
                   out.println("<h1> Descriptive Question</h1><br/><br/>");
                   question = db.PgetQuestionDesc(subject,Integer.parseInt(set));
                    for(String Sn : question)
            {
                out.println(Sn);
                //counter++;
            }
                    out.println("<br/><br/>");
                   }
               %>
               1.<input type="text" name="desc1" style="font-size: 18pt; height: 140px; width:380px;"><br/><br/>
               2.<input type="text" name="desc2" style="font-size: 18pt; height: 140px; width:380px;"><br/><br/>
               3.<input type="text" name="desc3" style="font-size: 18pt; height: 140px; width:380px;"><br/><br/>
               4.<input type="text" name="desc4" style="font-size: 18pt; height: 140px; width:380px;"><br/><br/>
               5.<input type="text" name="desc5" style="font-size: 18pt; height: 140px; width:380px;"><br/><br/>
               6.<input type="text" name="desc6" style="font-size: 18pt; height: 140px; width:380px;"><br/><br/>
               
             <input type="submit" value="Submit" />
            </form>
    </body>
</html>
