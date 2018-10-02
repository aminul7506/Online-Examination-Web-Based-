<%-- 
    Document   : descriptiveQues
    Created on : Dec 19, 2016, 12:03:28 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Descriptive Question!</h1>
       
        <form method="post" action="DescriptiveQues.do">
            Question<input type="text" name="descriptiveQues" style= "font-size: 18pt; height: 140px; width:380px;"><br/><br/><br/>
            Marks <input type="text" name="marks" /> <br/> <br/> <br/>
            <input type="submit" value="create" />
        </form>
    </body>
</html>
