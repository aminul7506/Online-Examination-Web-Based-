<%-- 
    Document   : guardianHomepage
    Created on : Dec 18, 2016, 4:04:01 PM
    Author     : Sojal
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
            String username = (String) session.getAttribute("username");
            if(username==null)
            {
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);
            }
        
             String username1 = (String) session.getAttribute("username");
             DataAccess db = new DataAccess();
             Integer guardian_id = db.getGuardianId(username);
             out.println("<h1>Guardian Home Page</h1></br>");
             out.println("<h1> Username : " + username + "</h1>");
             out.println("<h1> Guardian Id : " + guardian_id + "</h1>");
             out.println("<h1>List of Student Id you have : </h1>");
             List<Integer> sId = new ArrayList<Integer>();
             sId = db.getS_IdOfaGuardian(guardian_id);
             int len = sId.size();
             int counter = 1;
             for(int i = 0; i < len; i++){
                 out.println("<h1><pre>" + counter + "." + sId.get(i) +"    "  + 
                         db.getSubject1(sId.get(i)) + "</pre></h1>");
                 counter++;
             }
             %>
             
             <form method="post" action="guardianHomepage">
                 <h1>Please enter s_id </h1>
             Enter student id: <input type="text" name="s_id" /> <br/> <br/>
             
            <input type="submit" name = "type1" value="Add Student" />
             <input type="submit" name = "type1" value="Go to Student Profile" />
             <input type="submit" name = "type1" value="Log Out" />
             <input type="submit" name = "type1" value="Delete Account" />
             </form>
    </body>
</html>
