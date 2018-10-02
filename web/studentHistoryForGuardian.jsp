<%-- 
    Document   : studentHistoryForGuardian
    Created on : Dec 19, 2016, 10:22:00 AM
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
            String s_no = (String) session.getAttribute("s_id");
             DataAccess db = new DataAccess();
             Integer guardian_id = db.getGuardianId(username);
             Integer class_id = db.getStudentclass1(Integer.parseInt(s_no));
             out.println("<h1> Username : " + username + "</h1>");
             out.println("<h1> Guardian Id : " + guardian_id + "</h1>");
             out.println("<h1>Details of the student of student Id : " + s_no + "</h1></br>");
             List<Integer> qNo = new ArrayList<Integer>();
             List<Integer> total = new ArrayList<Integer>();
             qNo = db.getallq_IdOfaGuardian(class_id);
             total = db.getTotalMarksOfaQ_No(class_id);
             int len = qNo.size();System.out.println("dhuro " + total.size());
             out.println("<h1><pre>Code  Subject  Full Marks  Achieved Marks</pre></h1><br/>");
             for(int i = 0; i < len; i++){
                 out.println("<h1><pre>" + qNo.get(i) + "     " + db.getSubject1(qNo.get(i)) + 
                  "        " + total.get(i) + 
                         "      " + db.getAchievedMarks(qNo.get(i),Integer.parseInt(s_no))
                         + "</pre></h1>");
             }
       %>
    </body>
</html>
