/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DB.DataAccess;
import model.Subject;
import java.util.ArrayList;

/**
 *
 * @author samsung
 */
public class loginProcess extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static String subject1 = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        DataAccess db = new DataAccess();
        boolean exist = false;
        int ind = -1;
        if(type.equals("Student")){
            exist = db.existStudent(username, password);
            ind = 0;
        }
        else if(type.equals("Guardian")){
            exist = db.existGuardian(username, password);
            ind = 1;
        }
        else if(type.equals("Teacher")){
            exist = db.existTeacher(username, password);
            ind = 2;
        }
        if(exist == true && ind == 0)
        {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            RequestDispatcher rd = request.getRequestDispatcher("studentPage.jsp");
            rd.forward(request, response);
            System.out.println(username);
        }
        else if(exist  == true && ind == 1)
        {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            RequestDispatcher rd = request.getRequestDispatcher("guardianHomepage.jsp");
            rd.forward(request, response);
            System.out.println(username);
        }
         else if(exist  == true && ind == 2)
        {
            //HttpSession session = request.getSession();
            //session.setAttribute("username", username);
            //RequestDispatcher rd = request.getRequestDispatcher("teacherInterface.jsp");
            //rd.forward(request, response);
            //System.out.println(username);
             //DataAccess db = new DataAccess();
            ArrayList<Subject> subjects = db.getSubjects(db.getTeacherId(username));
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("subjects", subjects);
            RequestDispatcher rd = request.getRequestDispatcher("teacherInterface.jsp");
            rd.forward(request, response);
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
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
