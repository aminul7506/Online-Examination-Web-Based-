<%-- 
    Document   : mcq
    Created on : Dec 13, 2016, 11:37:59 AM
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
        <h1>MCQ Upload</h1>
        <form method="post" action="MCQUpload.do">
            MCQ <input type="text" name="mcq" /><br>
            Option1 <input type="text" name="option1" /> <br/>
            Option2 <input type="text" name="option2" /> <br/>
            Option3 <input type="text" name="option3" /> <br/>
            Option4 <input type="text" name="option4" /> <br/>
            Ans <input type="text" name="ans" /> <br/>
            <input type="submit" value="create" />
           
        </form>
    </body>
</html>
