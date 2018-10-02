/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import DB.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class MCQServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
             String mcq = request.getParameter("mcq");
             String option1 = request.getParameter("option1");
             String option2 = request.getParameter("option2");
             String option3 = request.getParameter("option3");
             String option4 = request.getParameter("option4");
              String username = (String) session.getAttribute("username");
             String ans = request.getParameter("ans");
             //HttpSession session = request.getSession();
             String subject = (String) session.getAttribute("subject");
             DataAccess db = new DataAccess();
             System.out.print(db.getTeacherId(username));
             System.out.print(username);
             db.createMCQ(mcq,option1,option2,option3,option4,ans,db.getTeacherId(username),subject);
            // HttpSession session = request.getSession();
             //String subject = (String) session.getAttribute("subject");
            // db.setQuesSub(subject);
             RequestDispatcher rd = request.getRequestDispatcher("mcq.jsp");
             rd.forward(request, response); 
    }
             // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


             
       
    

   
}
