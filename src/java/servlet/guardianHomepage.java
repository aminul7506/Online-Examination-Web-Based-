/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DB.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "guardianHomepage", urlPatterns = {"/guardianHomepage"})
public class guardianHomepage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String username =(String) session.getAttribute("username");System.out.println(username);
            /* TODO output your page here. You may use following sample code. */
            String s_id = request.getParameter("s_id");
            String type1 = request.getParameter("type1");
            if(type1.equals("Add Student")){
                DataAccess db = new DataAccess();
                String answer = db.isValidStudent(Integer.parseInt(s_id));System.out.println(answer);
                String answer1 = db.isValidGuardian(username);System.out.println(answer1);
                Integer guardian_id = db.getGuardianId(username);System.out.println(guardian_id);
                
                if(answer.equals("YES") && answer1.equals("YES")){
                    System.out.println("fuck you");
                    int count = db.insertIntoStudent_Guardian(Integer.parseInt(s_id),guardian_id);
                    System.out.println(count);
                     RequestDispatcher rd = request.getRequestDispatcher("guardianHomepage.jsp");
                rd.forward(request, response);
                }
            }
            else if(type1.equals("Go to Student Profile")){
                session.setAttribute("s_id", s_id);
                DataAccess db = new DataAccess();
                Integer guardian_id = db.getGuardianId(username);
                 List<Integer> sId = new ArrayList<Integer>();
                 sId = db.getS_IdOfaGuardian(guardian_id);
                 int flag  = sId.indexOf(Integer.parseInt(s_id));
                 if(flag != -1){
                        RequestDispatcher rd = request.getRequestDispatcher("studentHistoryForGuardian.jsp");
                         rd.forward(request, response);
                 }
                 else {
                     RequestDispatcher rd = request.getRequestDispatcher("guardianHomepage.jsp");
                rd.forward(request, response);
                 }
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
            db.deleteGuardian(username);
            request.getSession().invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
            //System.out.println(subject + "good");
          }
        }
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
