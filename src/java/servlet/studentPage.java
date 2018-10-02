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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sojal
 */
@WebServlet(name = "studentPage", urlPatterns = {"/studentPage"})
public class studentPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static int question_no = 0; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
         String username =(String) session.getAttribute("username");
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        String subject = request.getParameter("subject");
        // String username = request.getParameter("username");
        //String password = request.getParameter("password");
        String type = request.getParameter("type");
        String type1 = request.getParameter("type1");
        //DataAccess db = new DataAccess();
        //boolean exist = false;
        //if(type.equals("examinationQuestion")){//exist = db.existStudent(username, password);
//          HttpSession session = request.getSession();
          // System.out.println("hujk");
          if(type1.equals("Go to Question Paper") && type.equals("examinationQuestion")){
            session.setAttribute("subject", subject);
            RequestDispatcher rd = request.getRequestDispatcher("examQuestion.jsp");
            rd.forward(request, response);
            System.out.println(subject + "good");
          }
          else if(type1.equals("My Profile") && type.equals("examinationQuestion") ){
            session.setAttribute("subject", subject);
            RequestDispatcher rd = request.getRequestDispatcher("studentHistory.jsp");
            rd.forward(request, response);
            System.out.println(subject + "good");
          }
          else if(type1.equals("Go to Question Paper") && type.equals("practiceQuestion")){
            session.setAttribute("subject", subject);
            RequestDispatcher rd = request.getRequestDispatcher("practiceExamSet.jsp");
            rd.forward(request, response);
            //System.out.println(subject + "good");
          }
          else if(type1.equals("My Profile") && type.equals("practiceQuestion") ){
            session.setAttribute("subject", subject);
            RequestDispatcher rd = request.getRequestDispatcher("studentPracticeHistory.jsp");
            rd.forward(request, response);
            System.out.println(subject + "good");
          }
          else if(type1.equals("Log Out")){
            //session.setAttribute("subject", subject);
            request.getSession().invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
            //System.out.println(subject + "good");
          }
          else if(type1.equals("Delete Account")){
            //session.setAttribute("subject", subject);
             DataAccess db = new DataAccess();
            db.deleteStudent(username);
            request.getSession().invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
            //System.out.println(subject + "good");
          }
        }
   // }

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
