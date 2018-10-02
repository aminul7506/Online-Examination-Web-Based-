/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet; 
import DB.DataAccess;
import model.Question;
import model.QuestionPaper;
import model.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class teaIntServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String subject = request.getParameter("subject");
        String username = (String)session.getAttribute("username");
       // HttpSession session = request.getSession();
        session.setAttribute("subject", subject);
        session.setAttribute("username", username);
        String option = request.getParameter("option");
        if(option.equals("MCQ Upload")){
        RequestDispatcher rd = request.getRequestDispatcher("mcq.jsp");
        rd.forward(request, response);
        }
        else if(option.equals("Question Paper Upload")){
         DataAccess dao = new DataAccess();
         
         System.out.println(dao.getTeacherId(username));
        ArrayList<Question> questions = dao.getQuestions(dao.getTeacherId(username),subject);
        session.setAttribute("questions", questions);
        RequestDispatcher rd = request.getRequestDispatcher("questionPaper.jsp");
        rd.forward(request, response);
        }
        else if(option.equals("Descriptive Question Upload")){
        RequestDispatcher rd = request.getRequestDispatcher("descriptiveQues.jsp");
        rd.forward(request, response);
        }
        else if(option.equals("Available QuestionPaper")){
        DataAccess dao = new DataAccess();
        ArrayList<QuestionPaper> questionPapers = dao.getQuestionPaper(subject);
        session.setAttribute("questionPapers", questionPapers);
        RequestDispatcher rd = request.getRequestDispatcher("availableQuestionPaper.jsp");
        rd.forward(request, response);
        }
        else if(option.equals("Update Question")){
        //DataAccess dao = new DataAccess();
        //ArrayList<QuestionPaper> questionPapers = dao.getQuestionPaper(subject);
        //session.setAttribute("questionPapers", questionPapers);
        RequestDispatcher rd = request.getRequestDispatcher("upadateQuestion.jsp");
        rd.forward(request, response);
        }
        else if(option.equals("Delete Account")){
           // DataAccess db = new DataAccess();
            //db.deleteTeacher(username);
            request.getSession().invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }
        else {
              DataAccess dao = new DataAccess();
             ArrayList<Integer> students = dao.getStudentId1(subject);
           
             session.setAttribute("students", students);
            RequestDispatcher rd = request.getRequestDispatcher("answerCheck.jsp");
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
