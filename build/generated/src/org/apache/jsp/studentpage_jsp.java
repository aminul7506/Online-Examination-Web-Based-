package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import DB.DataAccess;

public final class studentpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("    <h1>Student Page</h1>\n");
      out.write("        \n");
      out.write("       \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         ");

            String username = (String) session.getAttribute("username");
            if(username==null)
            {
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);
            }
        
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        ");
 
             String username1 = (String) session.getAttribute("username");
             DataAccess db = new DataAccess();
             List<String> subjectName = new ArrayList<String>();
             
             int counter = 1;
             Integer class1 = db.getStudentclass(username1);
             //out.print(String.format("%s %s %s</br>","Student Name : ",name,username1));
             subjectName = db.getSubject(class1);
             out.println(String.format("%s  %s %s %s</br></br/>","No","Cls","Subject","t_id" ));
             for(String Sn : subjectName)
            {
                out.print(String.format("%d. %s %s</br>",counter,"      ",Sn ));
                out.print("\n");
                counter++;
            }
            
      out.write("\n");
      out.write("             <form method=\"post\" action=\"studentPage\">\n");
      out.write("                 <h1>Please enter subject according to subject code carefully and then go to examination </h1>\n");
      out.write("             Enter your subject: <input type=\"text\" name=\"subject\" /> <br/>\n");
      out.write("            \n");
      out.write("             </form>\n");
      out.write("             </br>\n");
      out.write("        <a href=\"practiceQuestion.jsp\">Practice Examination</a>\n");
      out.write("        </br>\n");
      out.write("        <a href=\"examQuestion.jsp\">Examination Question</a>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
