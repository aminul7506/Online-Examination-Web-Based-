/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import model.Question;
import model.QuestionPaper;
import model.Subject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Answer;
import model.answerShit;
import model.answerShitDesc;

/**
 *
 * @author Sojal
 */
public class DataAccess {
    String dbURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    String username = "sojal";
    String password = "sojal";
    Connection conn = null;
    static int q_id1;
    public DataAccess()
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if(conn!=null) System.out.println("Connection successfully established.");
            else System.out.println("Could not establish connection");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public Integer getStudentclass(String username){
       String selectStatement = "select class_id from STUDENT where username = ?";
       String result = "";
       Integer class_id = -1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                class_id = rs.getInt("class_id");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return class_id;
    }
    
    public Integer getStudentclass1(Integer s_id){
       String selectStatement = "select class_id from STUDENT where s_id = ?";
       String result = "";
       Integer class_id = -1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1,s_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                class_id = rs.getInt("class_id");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return class_id;
    }
    
     public List<Integer> getS_IdOfaGuardian(Integer guardian_id)
    {
        List<Integer> sId = new ArrayList<Integer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select s_id from student_guardian " +
                "where guardian_id = ?";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            //int i = subject.length();
            //int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            
            stmt.setInt(1,guardian_id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("s_id");
                sId.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return sId;
    }
    
     public List<Integer> getallq_IdOfaGuardian(Integer class_id)
    {
        List<Integer> qNo = new ArrayList<Integer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select distinct q_no from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID in( " +
            "select subject_id from subject where class_id = ?))) order by q_no asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            //int i = subject.length();
            //int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            
            stmt.setInt(1,class_id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("q_no");
                qNo.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return qNo;
    }
    
     public Integer getStudentId(String username){
       String selectStatement = "select s_id from STUDENT where username = ?";
       //String result = "";
       Integer s_id = -1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                s_id = rs.getInt("s_id");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return s_id;
    }
    
     
     
     public Integer getTeacherId(String username){
       String selectStatement = "select t_id from TEACHER where username = ?";
       //String result = "";
       Integer t_id = -1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                t_id = rs.getInt("t_id");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return t_id;
    }
      public Integer getGuardianId(String username){
       String selectStatement = "select guardian_id from GUARDIAN where username = ?";
       //String result = "";
       Integer g_id = -1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                g_id = rs.getInt("guardian_id");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return g_id;
    }
    
       public void deleteGuardian(String username)
     { 
         Integer g_id = getGuardianId(username);
        try
        {  
            
            String insertCommand = "delete from GUARDIAN where  guardian_id = ?";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,g_id);
            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
     
         
    }
    
      public void deleteStudent(String username)
     { 
         Integer s_id = getStudentId(username);
        try
        {  
            
            String insertCommand = "delete from STUDENT where  s_id = ?";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,s_id);
            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
     
         
    }
      
      
      public void deleteTeacher(String username)
     { 
         Integer t_id = getTeacherId(username);
        try
        {  
            
            String insertCommand = "delete from TEACHER where  t_id = ?";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,t_id);
            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
     
         
    }
       
    public void insertIntoStudentAnswerQuestion(HttpServletRequest request,String username,String subject)
     {
            int s_id = getStudentId(username);
            int q_no = getQ_no(subject);
            List<Integer> qNo = new ArrayList<Integer>();
            List<Integer> qNo1 = new ArrayList<Integer>();
            List<String> answer = new ArrayList<String>();
            List<String> answer1 = new ArrayList<String>();
            List<String> c_answer = new ArrayList<String>();
            qNo = getQ_Id_MCQ(subject);
            qNo1 = getQ_Id_DESC(subject);
            answerShit ans = new answerShit();
            answer = ans.getAnswer(request);
            answerShitDesc ans1 = new answerShitDesc();
            answer1 = ans1.getAnswerDesc(request);
            c_answer = getQuestionAnswer(subject);
            int len = qNo.size();
            int len1 = qNo1.size();
           
            try
                {
            for(int i = 0; i < len; i++){
                
                    String insertCommand = "insert into STUDENT_ANSWERS_QUESTION values(?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(insertCommand);

            stmt.setInt(1, s_id);System.out.println(s_id);
            stmt.setInt(2,qNo.get(i));System.out.println(qNo.get(i));
            stmt.setInt(3,q_no);System.out.println(q_no);
            stmt.setString(4,answer.get(i));System.out.println(answer.get(i));
            int marks = 0;
            if(c_answer.get(i).equals(answer.get(i)))marks = 1;System.out.println(c_answer.get(i) + marks);
            stmt.setInt(5,marks);
            //stmt.setString(6, password);
            int count = stmt.executeUpdate();
            //return count;
            }
            
            for(int i = 0; i < len1; i++){
                
                    String insertCommand = "insert into STUDENT_ANSWERS_QUESTION values(?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(insertCommand);

            stmt.setInt(1, s_id);System.out.println(s_id);
            stmt.setInt(2,qNo1.get(i));System.out.println(qNo1.get(i));
            stmt.setInt(3,q_no);System.out.println(q_no);
            stmt.setString(4,answer1.get(i));System.out.println(answer1.get(i));
            int marks = 0;
            //if(c_answer.get(i).equals(answer.get(i)))marks = 1;System.out.println(c_answer.get(i) + marks);
            stmt.setInt(5,marks);
            //stmt.setString(6, password);
            int count = stmt.executeUpdate();
            //return count;
            }
                }
        catch(Exception e)
        {
            e.printStackTrace();
            //return 0;
        }
            Integer total = 0;
             String selectStatement = "select sum(marks) from STUDENT_ANSWERS_QUESTION where " +
                     " s_id = ?  and q_id in " +
              "(select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?)))))";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1, s_id);
            stmt.setInt(2,j);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
            stmt.setString(5,subject);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                total = rs.getInt("sum(marks)");
                
                
                //counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try{
        String insertCommand = "insert into STUDENT_EXAMQUES values(?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(insertCommand);

            stmt.setInt(1, s_id);System.out.println(s_id);
           // stmt.setInt(2,qNo.get(i));System.out.println(qNo.get(i));
            stmt.setInt(2,q_no);System.out.println(q_no);
            //stmt.setString(3,answer.get(i));System.out.println(answer.get(i));
            //int marks = 0;
            //if(c_answer.get(i).equals(answer.get(i)))marks = 1;System.out.println(c_answer.get(i) + marks);
            stmt.setInt(3,total);
            //stmt.setString(6, password);
            int count = stmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
            
    }

    

     public void PinsertIntoStudentAnswerQuestion(HttpServletRequest request,String username,String subject,Integer q_no)
     {
            int s_id = getStudentId(username);
           // int q_no = PgetQ_no(subject);
            List<Integer> qNo = new ArrayList<Integer>();
            List<Integer> qNo1 = new ArrayList<Integer>();
            List<String> answer = new ArrayList<String>();
            List<String> answer1 = new ArrayList<String>();
            List<String> c_answer = new ArrayList<String>();
            qNo = PgetQ_Id_MCQ(subject,q_no);
            qNo1 = PgetQ_Id_DESC(subject,q_no);
            answerShit ans = new answerShit();
            answer = ans.getAnswer(request);
            answerShitDesc ans1 = new answerShitDesc();
            answer1 = ans1.getAnswerDesc(request);
            c_answer = PgetQuestionAnswer(subject,q_no);
            int len = qNo.size();
            int len1 = qNo1.size();
           
            try
                {
            for(int i = 0; i < len; i++){
                
                    String insertCommand = "insert into STUDENT_ANSWERS_QUESTION values(?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(insertCommand);

            stmt.setInt(1, s_id);System.out.println(s_id);
            stmt.setInt(2,qNo.get(i));System.out.println(qNo.get(i));
            stmt.setInt(3,q_no);System.out.println(q_no);
            stmt.setString(4,answer.get(i));System.out.println(answer.get(i));
            int marks = 0;
            if(c_answer.get(i).equals(answer.get(i)))marks = 1;System.out.println(c_answer.get(i) + "answer" + marks + " give" + answer.get(i));
            System.out.println("dhur " + marks);
            stmt.setInt(5,marks);
            //stmt.setString(6, password);
            int count = stmt.executeUpdate();
            //return count;
            }
            
            for(int i = 0; i < len1; i++){
                
                    String insertCommand = "insert into STUDENT_ANSWERS_QUESTION values(?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(insertCommand);

            stmt.setInt(1, s_id);System.out.println(s_id);
            stmt.setInt(2,qNo1.get(i));System.out.println(qNo1.get(i));
            stmt.setInt(3,q_no);System.out.println(q_no);
            stmt.setString(4,answer1.get(i));System.out.println(answer1.get(i));
            int marks = 0;
            //if(c_answer.get(i).equals(answer.get(i)))marks = 1;System.out.println(c_answer.get(i) + marks);
            stmt.setInt(5,marks);
            //stmt.setString(6, password);
            int count = stmt.executeUpdate();
            //return count;
            }
                }
        catch(Exception e)
        {
            e.printStackTrace();
            //return 0;
        }
            Integer total = 0;
             String selectStatement = "select sum(marks) from STUDENT_ANSWERS_QUESTION where " +
                     " s_id = ?  and q_id in " +
              "(select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ? ))";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1, s_id);
            stmt.setInt(2,j);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
           // stmt.setString(5,subject);
            stmt.setInt(5,q_no);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                total = rs.getInt("sum(marks)");
                
                
                //counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try{
        String insertCommand = "insert into STUDENT_PRACTICEEXAMINATION values(?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(insertCommand);

            stmt.setInt(1, s_id);System.out.println(s_id);
           // stmt.setInt(2,qNo.get(i));System.out.println(qNo.get(i));
            stmt.setInt(2,q_no);System.out.println(q_no);
            //stmt.setString(3,answer.get(i));System.out.println(answer.get(i));
            //int marks = 0;
            //if(c_answer.get(i).equals(answer.get(i)))marks = 1;System.out.println(c_answer.get(i) + marks);
            stmt.setInt(3,total);
            //stmt.setString(6, password);
            int count = stmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
            
    }

     public Integer getQ_no(String subject){
       String selectStatement = "select max(q_no) from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?))";
       //String result = "";
       Integer q_no = -1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1,subject);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                q_no = rs.getInt("max(q_no)");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return q_no;
    }       
    
     
      public Integer getAchievedMarks(Integer q_no,Integer s_id){
       String selectStatement = "select NVL(achieved_marks,0) m from STUDENT_EXAMQUES "
               + " where q_no  = ? and s_id = ?";
       //String result = "";
       Integer achieved_marks = 0;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1,q_no);
            stmt.setInt(2,s_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                achieved_marks = rs.getInt("m");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return achieved_marks;
    }       
    
     
     
     
    public String getSubject1(Integer q_no){
       String selectStatement = "select distinct subject_id from QUESTION_CLASS_SUBJECT where q_id in(" +
                        "select q_id from QUESTIONPAPER_QUESTION where q_no = ?)";
       String result = "";
       //Integer q_no = -1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1,q_no);
            ResultSet rs = stmt.executeQuery();
            //result = rs.getString("subject_id");
            
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                result = rs.getString("subject_id");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public ArrayList<Integer> getTotalMarksOfaQ_No(Integer class_id){
       String selectStatement = "select total_marks from QUESTIONPAPER where q_no in(" +
            "select distinct q_no from(" +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER)" +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID in(" +
            "select subject_id from subject where class_id = ?)))) order by q_no asc";
       //String result = "";
       Integer total1 = 0;
        ArrayList<Integer> total = new ArrayList<Integer>();
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1,class_id);
            ResultSet rs = stmt.executeQuery();
            //result = rs.getString("subject_id");
           
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                total1 = rs.getInt("total_marks");
                total.add(total1);
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return total;
    }
    
    
     public ArrayList<Integer> PgetQ_no(String subject){
       String selectStatement = "select distinct q_no from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from PRACTICEQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?)) order by q_no asc";
       //String result = "";
       //Integer q_no = -1;
       ArrayList<Integer> q_no = new ArrayList<Integer>();
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1,subject);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
               Integer q_no1 = rs.getInt("q_no");
               q_no.add(q_no1);
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return q_no;
    }
    
    
    public boolean isQuestionAvailable(Integer s_id,String subject)
    {
        Integer q_no = getQ_no(subject);
        Integer result = -1;
        String selectStatement = "select ACHIEVED_MARKS from STUDENT_EXAMQUES where s_id = ? and q_no = ?";
          try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1,s_id);
            stmt.setInt(2,q_no);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                result = rs.getInt("ACHIEVED_MARKS");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(result == -1)return false;
        else return true;
        
    }
    
    public boolean PisQuestionAvailable(Integer s_id,String subject,Integer q_no)
    {
       // Integer q_no = getQ_no(subject);
        Integer result = -1;
        String selectStatement = "select ACHIEVED_MARKS from STUDENT_PRACTICEEXAMINATION where s_id = ? and q_no = ?";
          try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1,s_id);
            stmt.setInt(2,q_no);//System.out.println("fi" + q_no);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                //String class_id = rs.getString("CLASS_ID");
//                String firstName  = rs.getString("firstName");
                result = rs.getInt("ACHIEVED_MARKS");
                //result = firstName + "   " + lastname;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(result == -1)return false;
        else return true;
        
    }
    
    
     public List<String> getSubject(int cls_id)
    {
        List<String> subjectName = new ArrayList<String>();
        String selectStatement = "select * from subject join class using(CLASS_ID) where CLASS_ID ="
                + cls_id;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String class_id = rs.getString("CLASS_ID");
                String subject_id = rs.getString("SUBJECT_ID");
                Integer t_id = rs.getInt("t_id");
                String result ="<pre>" + class_id + "      " + subject_id + 
                        "         " + String.valueOf(t_id) + "</pre>";
                subjectName.add(result);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return subjectName;
    }
    
    
    
    
    public List<String> getQuestion(String subject)
    {
        List<String> question = new ArrayList<String>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select question from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?))))" +
                "and q_id in(SELECT q_id from MCQ_QUESTION) order by q_id asc";
        String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION " +
                        "where q_id IN (select q_id from QUESTION " +
                        "where t_id = (SELECT t_id from subject " +
                        "where class_id = ? and subject_id = ?)) " +
                        "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
                        "where SUBJECT_ID = ?) and q_id in( " +
                        "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
                        "select q_no from QUESTIONPAPER_QUESTION " +
                        "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
                        "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
                        "where SUBJECT_ID = ?))))" +
                "and q_id in(SELECT q_id from MCQ_QUESTION) order by q_id asc";
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //String k = String.valueOf(subject.charAt(i-1)) + String.valueOf(subject.charAt(i-2));
           // int j = Integer.parseInt(k);
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
            stmt1.setInt(1,j);
            stmt1.setString(2,subject);
            stmt1.setString(3,subject);
            stmt1.setString(4,subject);
            ResultSet rs = stmt.executeQuery();
            ResultSet rs1 = stmt1.executeQuery();
            while(rs.next() && rs1.next())
            {
                String questionTemp = rs.getString("question");
                String result = counter + ". " + questionTemp + "<br/>";
                String questionTemp1 = rs1.getString("option1");
                String questionTemp2 = rs1.getString("option2");
                String questionTemp3 = rs1.getString("option3");
                String questionTemp4 = rs1.getString("option4");
                result += "(a)" + questionTemp1 + "(b)" + questionTemp2 +
                        "(c)" + questionTemp3 + "(d)" + questionTemp4 + "<br/>";
                question.add(result);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return question;
    }
    
    
     public List<String> PgetQuestion(String subject,Integer q_no)
    {
        List<String> question = new ArrayList<String>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select question from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ? )" +
                "and q_id in(SELECT q_id from MCQ_QUESTION) order by q_id asc";
        String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION " +
                        "where q_id IN (select q_id from QUESTION " +
                        "where t_id = (SELECT t_id from subject " +
                        "where class_id = ? and subject_id = ?)) " +
                        "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
                        "where SUBJECT_ID = ?) and q_id in( " +
                        "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ?)" +
                "and q_id in(SELECT q_id from MCQ_QUESTION) order by q_id asc";
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //String k = String.valueOf(subject.charAt(i-1)) + String.valueOf(subject.charAt(i-2));
           // int j = Integer.parseInt(k);
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setInt(4,q_no);
            stmt1.setInt(1,j);
            stmt1.setString(2,subject);
            stmt1.setString(3,subject);
            stmt1.setInt(4,q_no);
            ResultSet rs = stmt.executeQuery();
            ResultSet rs1 = stmt1.executeQuery();
            while(rs.next() && rs1.next())
            {
                String questionTemp = rs.getString("question");
                String result = counter + ". " + questionTemp + "<br/>";
                String questionTemp1 = rs1.getString("option1");
                String questionTemp2 = rs1.getString("option2");
                String questionTemp3 = rs1.getString("option3");
                String questionTemp4 = rs1.getString("option4");
                result += "(a)" + questionTemp1 + "(b)" + questionTemp2 +
                        "(c)" + questionTemp3 + "(d)" + questionTemp4 + "<br/>";
                question.add(result);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return question;
    }
    
    
     public List<String> getQuestionDesc(String subject)
    {
        List<String> question = new ArrayList<String>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select question from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?))))" +
                "and q_id in(SELECT q_id from DESCRIPTIVE_QUESTION) order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //String k = String.valueOf(subject.charAt(i-1)) + String.valueOf(subject.charAt(i-2));
           // int j = Integer.parseInt(k);
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String questionTemp = rs.getString("question");
                String result = counter + ". " + questionTemp + "<br/>";
                question.add(result);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return question;
    }
    
     public List<String> PgetQuestionDesc(String subject,Integer q_no)
    {
        List<String> question = new ArrayList<String>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select question from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ?)" +
                "and q_id in(SELECT q_id from DESCRIPTIVE_QUESTION) order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //String k = String.valueOf(subject.charAt(i-1)) + String.valueOf(subject.charAt(i-2));
           // int j = Integer.parseInt(k);
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setInt(4,q_no);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String questionTemp = rs.getString("question");
                String result = counter + ". " + questionTemp + "<br/>";
                question.add(result);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return question;
    }
    
     
    public List<String> getAllDetailsofaSubjectforaStudent(String username,String subject){
         List<String> details = new ArrayList<String>();
         int s_id = getStudentId(username);
         String selectStatement = "select q_id,q_no from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in(" +
                "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
                "SELECT distinct q_no from STUDENT_ANSWERS_QUESTION) " + 
                 "and q_no in(select q_no from STUDENT_EXAMQUES) and " +
                 "q_id in(" +
                "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))) " +
                 "and q_id in(SELECT q_id from MCQ_QUESTION)" +
                "order by q_id asc";
         String selectStatement1 = "select q_id,question from QUESTION where q_id in(" +
                "select q_id from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in(" +
                "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
            "SELECT  distinct q_no from STUDENT_ANSWERS_QUESTION where q_id in(" +
                "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
                 "and q_no in(SELECT q_no from STUDENT_EXAMQUES)))" +
                 "and q_id in(SELECT q_id from MCQ_QUESTION) " +
                "order by q_id asc";
         String selectStatement2 = "select q_id,option1,option2,option3,option4 from mcq_QUESTION where q_id in(" +
                "select q_id from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in(" +
                "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
                "SELECT  distinct q_no from STUDENT_ANSWERS_QUESTION where q_id in(" +
                "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
                 "and q_no in(SELECT q_no from STUDENT_EXAMQUES)))" +
                 "and q_id in(SELECT q_id from MCQ_QUESTION)" +
                "order by q_id asc";
          try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            PreparedStatement stmt2 = conn.prepareStatement(selectStatement2);
            stmt.setInt(1, s_id);
            stmt.setString(2,subject);
            ResultSet rs = stmt.executeQuery();
            stmt1.setInt(1, s_id);
            stmt1.setString(2,subject);
            ResultSet rs1 = stmt1.executeQuery();
            //ResultSet rs2 = stmt1.executeQuery();
            stmt2.setInt(1, s_id);
            stmt2.setString(2,subject);
            ResultSet rs2 = stmt2.executeQuery();
            while(rs.next() && rs1.next() && rs2.next())
            {
                Integer q_no = rs.getInt("q_no");
                Integer q_id = rs.getInt("q_id");
                String question = rs1.getString("question");
                String option1 = rs2.getString("option1");
                String option2 = rs2.getString("option2");
                String option3 = rs2.getString("option3");
                String option4 = rs2.getString("option4");
                String answer = studentAnswerPerQuestion(s_id,q_no,q_id);
                String correct_answer = correctAnswerPerQuestion(q_id); 
                String final1 = "Question Code : " + q_no + "<br/>" + 
                        question + "<br/>" + "(a)" + option1 + " (b)" + option2 +
                        " (c)" + option3 + " (d)" + option4 + "<br/>" + 
                        "Your given answer : " + answer + "<br/>" +
                        "Correct Answer : " + correct_answer;
                details.add(final1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return details;
    }
    
    
     public List<String> PgetAllDetailsofaSubjectforaStudent(String username,String subject){
         List<String> details = new ArrayList<String>();
         int s_id = getStudentId(username);
         String selectStatement = "select q_id,q_no from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in(" +
                "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
                "SELECT distinct q_no from STUDENT_ANSWERS_QUESTION) " + 
                 "and q_no in(select q_no from STUDENT_PRACTICEEXAMINATION) and " +
                 "q_id in(" +
                "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))) " +
                 "and q_id in(SELECT q_id from MCQ_QUESTION)" +
                "order by q_id asc";
         String selectStatement1 = "select q_id,question from QUESTION where q_id in(" +
                "select q_id from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in(" +
                "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
            "SELECT  distinct q_no from STUDENT_ANSWERS_QUESTION where q_id in(" +
                "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
                 "and q_no in(SELECT q_no from STUDENT_PRACTICEEXAMINATION)))" +
                 "and q_id in(SELECT q_id from MCQ_QUESTION) " +
                "order by q_id asc";
         String selectStatement2 = "select q_id,option1,option2,option3,option4 from mcq_QUESTION where q_id in(" +
                "select q_id from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in(" +
                "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
                "SELECT  distinct q_no from STUDENT_ANSWERS_QUESTION where q_id in(" +
                "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
                 "and q_no in(SELECT q_no from STUDENT_PRACTICEEXAMINATION)))" +
                 "and q_id in(SELECT q_id from MCQ_QUESTION)" +
                "order by q_id asc";
          try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            PreparedStatement stmt2 = conn.prepareStatement(selectStatement2);
            stmt.setInt(1, s_id);
            stmt.setString(2,subject);
            ResultSet rs = stmt.executeQuery();
            stmt1.setInt(1, s_id);
            stmt1.setString(2,subject);
            ResultSet rs1 = stmt1.executeQuery();
            //ResultSet rs2 = stmt1.executeQuery();
            stmt2.setInt(1, s_id);
            stmt2.setString(2,subject);
            ResultSet rs2 = stmt2.executeQuery();
            while(rs.next() && rs1.next() && rs2.next())
            {
                Integer q_no = rs.getInt("q_no");
                Integer q_id = rs.getInt("q_id");
                String question = rs1.getString("question");
                String option1 = rs2.getString("option1");
                String option2 = rs2.getString("option2");
                String option3 = rs2.getString("option3");
                String option4 = rs2.getString("option4");
                String answer = studentAnswerPerQuestion(s_id,q_no,q_id);
                String correct_answer = correctAnswerPerQuestion(q_id); 
                String final1 = "Question Code : " + q_no + "<br/>" + 
                        question + "<br/>" + "(a)" + option1 + " (b)" + option2 +
                        " (c)" + option3 + " (d)" + option4 + "<br/>" + 
                        "Your given answer : " + answer + "<br/>" +
                        "Correct Answer : " + correct_answer;
                details.add(final1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return details;
    }
    
    public List<String> getAllDetailsofaSubjectforaStudentDesc(String username,String subject){
         List<String> details = new ArrayList<String>();
         int s_id = getStudentId(username);
         String selectStatement = "select q_id,q_no from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in( " +
            "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
            "SELECT distinct q_no from STUDENT_ANSWERS_QUESTION) " +
            "and q_no in(select q_no from STUDENT_EXAMQUES)" +
            " and q_id in(" +
            "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
            "and q_id in(SELECT q_id from DESCRIPTIVE_QUESTION) " +
            "order by q_id asc";
         String selectStatement1 = "select q_id,question from QUESTION where q_id in( " +
            "select q_id from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in( " +
            "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
            "SELECT  distinct q_no from STUDENT_ANSWERS_QUESTION where q_id in(" +
            "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
            "and q_no in(SELECT q_no from STUDENT_EXAMQUES)))" +
            "and q_id in(SELECT q_id from DESCRIPTIVE_QUESTION)" +
            "order by q_id asc";
        
          try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            stmt.setInt(1, s_id);
            stmt.setString(2,subject);
            ResultSet rs = stmt.executeQuery();
            stmt1.setInt(1, s_id);
            stmt1.setString(2,subject);
            ResultSet rs1 = stmt1.executeQuery();
            while(rs.next() && rs1.next())
            {
                Integer q_no = rs.getInt("q_no");
                Integer q_id = rs.getInt("q_id");
                Integer marks = 0,marks1 = 0;
                String selectStatement2 = "select marks from DESCRIPTIVE_QUESTION where q_id = ?";
                String selectStatement3 = "SELECT marks from STUDENT_ANSWERS_QUESTION " + 
                        "where s_id = ? and q_id = ?";
                try
                    {    
                        PreparedStatement stmt2 = conn.prepareStatement(selectStatement2);
                        PreparedStatement stmt3 = conn.prepareStatement(selectStatement3);
                        stmt2.setInt(1, q_id);
                        ResultSet rs2 = stmt2.executeQuery();
                        stmt2.setInt(1, q_id);
                        stmt3.setInt(1,s_id);
                        stmt3.setInt(2,q_id);
                        ResultSet rs3 = stmt3.executeQuery();
                        while(rs2.next() && rs3.next()){
                                marks = rs2.getInt("marks");
                                marks1 = rs3.getInt("marks");
                        }
             }catch(Exception e){
                 e.printStackTrace();
             }
                String question = rs1.getString("question");
                String answer = studentAnswerPerQuestion(s_id,q_no,q_id);
                String final1 = "Question Code : " + q_no + "<br/>" + 
                        question + "<br/>" + 
                        "Your given answer : " + answer + "<br/>" +
                        "Marks of the Question : " + marks + "<br/>" +
                        "You have got : " + marks1;
                details.add(final1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return details;
    }
    
    
    public List<String> PgetAllDetailsofaSubjectforaStudentDesc(String username,String subject){
         List<String> details = new ArrayList<String>();
         int s_id = getStudentId(username);
         String selectStatement = "select q_id,q_no from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in( " +
            "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
            "SELECT distinct q_no from STUDENT_ANSWERS_QUESTION) " +
            "and q_no in(select q_no from STUDENT_PRACTICEEXAMINATION)" +
            " and q_id in(" +
            "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
            "and q_id in(SELECT q_id from DESCRIPTIVE_QUESTION) " +
            "order by q_id asc";
         String selectStatement1 = "select q_id,question from QUESTION where q_id in( " +
            "select q_id from STUDENT_ANSWERS_QUESTION where  s_id = ? and q_id in( " +
            "select distinct q_id from STUDENT_ANSWERS_QUESTION where (q_no in(" +
            "SELECT  distinct q_no from STUDENT_ANSWERS_QUESTION where q_id in(" +
            "select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?)))" +
            "and q_no in(SELECT q_no from STUDENT_PRACTICEEXAMINATION)))" +
            "and q_id in(SELECT q_id from DESCRIPTIVE_QUESTION)" +
            "order by q_id asc";
        
          try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            stmt.setInt(1, s_id);
            stmt.setString(2,subject);
            ResultSet rs = stmt.executeQuery();
            stmt1.setInt(1, s_id);
            stmt1.setString(2,subject);
            ResultSet rs1 = stmt1.executeQuery();
            while(rs.next() && rs1.next())
            {
                Integer q_no = rs.getInt("q_no");
                Integer q_id = rs.getInt("q_id");
                Integer marks = 0,marks1 = 0;
                String selectStatement2 = "select marks from DESCRIPTIVE_QUESTION where q_id = ?";
                String selectStatement3 = "SELECT marks from STUDENT_ANSWERS_QUESTION " + 
                        "where s_id = ? and q_id = ?";
                try
                    {    
                        PreparedStatement stmt2 = conn.prepareStatement(selectStatement2);
                        PreparedStatement stmt3 = conn.prepareStatement(selectStatement3);
                        stmt2.setInt(1, q_id);
                        ResultSet rs2 = stmt2.executeQuery();
                        stmt2.setInt(1, q_id);
                        stmt3.setInt(1,s_id);
                        stmt3.setInt(2,q_id);
                        ResultSet rs3 = stmt3.executeQuery();
                        while(rs2.next() && rs3.next()){
                                marks = rs2.getInt("marks");
                                marks1 = rs3.getInt("marks");
                        }
             }catch(Exception e){
                 e.printStackTrace();
             }
                String question = rs1.getString("question");
                String answer = studentAnswerPerQuestion(s_id,q_no,q_id);
                String final1 = "Question Code : " + q_no + "<br/>" + 
                        question + "<br/>" + 
                        "Your given answer : " + answer + "<br/>" +
                        "Marks of the Question : " + marks + "<br/>" +
                        "You have got : " + marks1;
                details.add(final1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return details;
    }
    
     public String studentAnswerPerQuestion(Integer s_id,Integer q_no,Integer q_id){
        try{
       CallableStatement cstmt = conn.prepareCall("{? = call GET_STUDENT_ANSWER(?,?,?)}");
             cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             cstmt.setInt(2, s_id);
             cstmt.setInt(3, q_no);
             cstmt.setInt(4,q_id);
             //cstmt.setString(5, deathDate);
             
             Boolean check = cstmt.execute();
             System.out.println(check);
             //String k = cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             String answer = cstmt.getString(1);
             System.out.println(answer);
             return answer;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        
    }
    
    
    public String isValidStudent(Integer s_id){
        try{
       CallableStatement cstmt = conn.prepareCall("{? = call IS_VALID_S_ID(?)}");
             cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             cstmt.setInt(2, s_id);
             //cstmt.setString(5, deathDate);
             
             Boolean check = cstmt.execute();
             System.out.println(check);
             //String k = cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             String answer = cstmt.getString(1);
             System.out.println(answer);
             return answer;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        
    }
    
    public String isValidGuardian(String username){
        try{
       CallableStatement cstmt = conn.prepareCall("{? = call IS_VALID_GUARDIAN_ID(?)}");
             cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             cstmt.setString(2,username);
             //cstmt.setString(5, deathDate);
             
             Boolean check = cstmt.execute();
             System.out.println(check);
             //String k = cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             String answer = cstmt.getString(1);
             System.out.println(answer);
             return answer;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        
    }
    
    public String correctAnswerPerQuestion(Integer q_id){
        try{
       CallableStatement cstmt = conn.prepareCall("{? = call GET_CORRECT_ANSWER(?)}");
             cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             cstmt.setInt(2,q_id);
             //cstmt.setString(5, deathDate);
             
             Boolean check = cstmt.execute();
             System.out.println(check);
             //String k = cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
             String answer = cstmt.getString(1);
             System.out.println(answer);
             return answer;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        
    }
    
    public List<String> getQuestionAnswer(String subject)
    {
        List<String> questionAns = new ArrayList<String>();
        
        String selectStatement1 = "select answer from MCQ_QUESTION " +
                        "where q_id IN (select q_id from QUESTION " +
                        "where t_id = (SELECT t_id from subject " +
                        "where class_id = ? and subject_id = ?)) " +
                        "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
                        "where SUBJECT_ID = ?) and q_id in( " +
                        "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
                        "select q_no from QUESTIONPAPER_QUESTION " +
                        "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
                        "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
                        "where SUBJECT_ID = ?)))) order by q_id asc";
        int counter = 1;
        try
        {    
            //PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            
            stmt1.setInt(1,j);
            stmt1.setString(2,subject);
            stmt1.setString(3,subject);
            stmt1.setString(4,subject);
           
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next())
            {
                String ans = rs1.getString("answer");
               
                questionAns.add(ans);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return questionAns;
    }
    
    
    public List<String> PgetQuestionAnswer(String subject,Integer q_no)
    {
        List<String> questionAns = new ArrayList<String>();
        
        String selectStatement1 = "select answer from MCQ_QUESTION " +
                        "where q_id IN (select q_id from QUESTION " +
                        "where t_id = (SELECT t_id from subject " +
                        "where class_id = ? and subject_id = ?)) " +
                        "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
                        "where SUBJECT_ID = ?) and q_id in( " +
                        "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ?)order by q_id asc";
        int counter = 1;
        try
        {    
            //PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            
            stmt1.setInt(1,j);
            stmt1.setString(2,subject);
            stmt1.setString(3,subject);
            stmt1.setInt(4,q_no);
           
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next())
            {
                String ans = rs1.getString("answer");
               
                questionAns.add(ans);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return questionAns;
    }
    
    
    public List<String> getStudentSubjectDetails(String username,String subject)
    {
        List<String> details = new ArrayList<String>();
        String selectStatement = "select total_marks from QUESTIONPAPER where q_no in(" +
                    "select distinct q_no from(" +
                    "select q_no from QUESTIONPAPER_QUESTION " +
                    "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
                    "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
                    "where SUBJECT_ID = ?))) ";
        String selectStatement1 = "select q_no,ACHIEVED_MARKS from STUDENT_EXAMQUES " +
            "where s_id = ? and q_no in(" +
            "select distinct q_no from(" +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?))) order by q_no asc";
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            
            //int i = subject.length();
            //int j = subject.charAt(i-1) - 48;
            int s_id = getStudentId(username);
            stmt.setString(1,subject);
            stmt1.setInt(1,s_id);
            stmt1.setString(2,subject);
            //stmt1.setString(3,subject);
            //stmt1.setString(4,subject);
           ResultSet rs = stmt.executeQuery();
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next() && rs.next())
            {
                String no = rs1.getString("q_no");
               String marks = rs1.getString("ACHIEVED_MARKS");
               String total = rs.getString("total_marks");
               String answer = "<h1><pre>" + no +  "    " + total + "    " +  marks + "</pre></h1>";
                details.add(answer);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return details;
    }
    
    
     public List<String> PgetStudentSubjectDetails(String username,String subject)
    {
        List<String> details = new ArrayList<String>();
        String selectStatement = "select total_marks from QUESTIONPAPER where q_no in(" +
                    "select distinct q_no from(" +
                    "select q_no from QUESTIONPAPER_QUESTION " +
                    "where q_no in(select q_no from PRACTICEQUESTION_QUESTIONPAPER) " +
                    "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
                    "where SUBJECT_ID = ?)))";
        String selectStatement1 = "select q_no,ACHIEVED_MARKS from STUDENT_PRACTICEEXAMINATION " +
            "where s_id = ? and q_no in(" +
            "select distinct q_no from(" +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from PRACTICEQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?))) order by q_no asc";
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
            
            //int i = subject.length();
            //int j = subject.charAt(i-1) - 48;
            int s_id = getStudentId(username);
            stmt.setString(1,subject);
            stmt1.setInt(1,s_id);
            stmt1.setString(2,subject);
            //stmt1.setString(3,subject);
            //stmt1.setString(4,subject);
           ResultSet rs = stmt.executeQuery();
            ResultSet rs1 = stmt1.executeQuery();
            while(rs1.next() && rs.next())
            {
                String no = rs1.getString("q_no");
               String marks = rs1.getString("ACHIEVED_MARKS");
               String total = rs.getString("total_marks");
               String answer = "<h1><pre>" + no +  "    " + total + "    " +  marks + "</pre></h1>";
                details.add(answer);
                //System.out.println();
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return details;
    }
    
    public List<Integer> getQ_Id(String subject)
    {
        List<Integer> qId = new ArrayList<Integer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?)))) order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("q_id");
                qId.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return qId;
    }
    
    
    
    public List<Integer> PgetQ_Id(String subject,Integer q_no)
    {
        List<Integer> qId = new ArrayList<Integer>();
       //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ?)order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setInt(4,q_no);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("q_id");
                qId.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return qId;
    }
    
    
    public List<Integer> getQ_Id_MCQ(String subject)
    {
        List<Integer> qId = new ArrayList<Integer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?))))" +
                "and q_id in(select q_id from MCQ_QUESTION) order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("q_id");
                qId.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return qId;
    }
    
    
    public List<Integer> PgetQ_Id_MCQ(String subject,Integer q_no)
    {
        List<Integer> qId = new ArrayList<Integer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ?)" +
                "and q_id in(select q_id from MCQ_QUESTION) order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setInt(4,q_no);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("q_id");
                qId.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return qId;
    }
    
   public List<Integer> getQ_Id_DESC(String subject)
    {
        List<Integer> qId = new ArrayList<Integer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from( " +
            "select q_no from QUESTIONPAPER_QUESTION " +
            "where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER) " +
            "and q_id in(select q_id from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?))))" +
                "and q_id in(select q_id from DESCRIPTIVE_QUESTION) order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("q_id");
                qId.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return qId;
    }
    
    public int createAccountForTeacher(String firstName,String lastName,String username,String password)
    {
        try
        {
            String insertCommand = "insert into TEACHER values(?,?,?,TEACHER_ID_SEQ.NEXTVAL,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, password);
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
    
     public List<Integer> PgetQ_Id_DESC(String subject,Integer q_no)
    {
        List<Integer> qId = new ArrayList<Integer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement = "select q_id from QUESTION " +
            "where t_id = (SELECT t_id from subject " +
            "where class_id = ? and subject_id = ?) " +
            "and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT " +
            "where SUBJECT_ID = ?) " +
            "and q_id in( " +
            "SELECT q_id from QUESTIONPAPER_QUESTION where q_no = ?)" +
                "and q_id in(select q_id from DESCRIPTIVE_QUESTION) order by q_id asc";
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setInt(4,q_no);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Integer q_id = rs.getInt("q_id");
                qId.add(q_id);
                
                counter++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return qId;
    }
    
     
       public int createAccountForGuardian(String firstName,String lastName,String username,String password)
    {
        try
        {
            String insertCommand = "insert into GUARDIAN values(?,?,?,GUARDIAN_ID_SEQ.NEXTVAL,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, password);
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
   
       public int insertIntoStudent_Guardian(Integer s_id,Integer guardian_id)
    {
        try
        {
            String insertCommand = "insert into STUDENT_GUARDIAN values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, s_id);
            stmt.setInt(2, guardian_id);
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
       
       
       
    
      
      
     public int createAccountForStudent(String firstName,String lastName,Integer cls_roll,Integer class_id,String username, String password)
    {
        try
        {
            String insertCommand = "insert into STUDENT values(?,?,STUDENT_ID_SEQ.NEXTVAL,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, cls_roll);
            stmt.setInt(4, class_id);
            stmt.setString(5, username);
            stmt.setString(6, password);
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
    
   
    public boolean existStudent(String username,String password)
    {
        try
        {
            String query = "select * from STUDENT where username = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    public boolean existTeacher(String username,String password)
    {
        try
        {
            String query = "select * from TEACHER where username = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
    }
         public boolean existGuardian(String username,String password)
    {
        try
        {
            String query = "select * from GUARDIAN where username = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
    }
         
         
         //trishna starts here
         
         
         
         public void createMCQ(String mcq, String option1, String option2, 
            String option3, String option4,String ans,Integer t_id,String sub  )
        
    { 
        try
        {
            String insertCommand = "insert into QUESTION values(QUESTION_ID_SEQ.NEXTVAL,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, t_id);
            stmt.setString(2, mcq);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        try{
             CallableStatement cstmt = conn.prepareCall("{? = call QUESTION_ID}");
             cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
             cstmt.execute();
              q_id1 = cstmt.getInt(1);
              System.out.println("eka" + q_id1);
            
             
        }catch(Exception e){
            e.printStackTrace();
           
        }
        
        try
        {
            String insertCommand = "insert into MCQ_QUESTION values(1,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
             stmt.setInt(1,q_id1);
            stmt.setString(2,option1);
            stmt.setString(3,option2);
            System.out.println("mcq" + q_id1);
            stmt.setString(4,option3);
            stmt.setString(5,option4);
            stmt.setString(6,ans);
            int count=stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        try
        {  // System.out.println(sub);
            String insertCommand = "insert into QUESTION_CLASS_SUBJECT values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,sub);
            stmt.setInt(2,q_id1);
            System.out.println(q_id1);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



public void createDescriptive(String descriptiveQues,int marks,int t_id ,String sub)
        
    { 
        try
        {
            String insertCommand = "insert into QUESTION values(QUESTION_ID_SEQ.NEXTVAL,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, t_id);
            stmt.setString(2, descriptiveQues);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
        try{
             CallableStatement cstmt = conn.prepareCall("{? = call QUESTION_ID}");
             cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
             cstmt.execute();
              q_id1 = cstmt.getInt(1);
             
        }catch(Exception e){
            e.printStackTrace();
           
        }
        
        try
        {
            String insertCommand = "insert into DESCRIPTIVE_QUESTION values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,marks);
            stmt.setInt(2,q_id1);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
         try
        {  // System.out.println(sub);
            String insertCommand = "insert into QUESTION_CLASS_SUBJECT values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,sub);
            stmt.setInt(2,q_id1);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

   
     public ArrayList<Subject> getSubjects(int tId)
    {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        
        String selectStatement = "select subject_id , class_id from SUBJECT where t_id = ?";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1, tId);
            ResultSet rs = stmt.executeQuery();
          
            while(rs.next())
            {
                
                int classId = rs.getInt("class_id");
                String subId = rs.getString("subject_id");
                Subject sub = new Subject(classId, tId, subId);
                subjects.add(sub);
               
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return subjects;

    }
     
    /* public void setQuesSub(String sub)
        
    { 
         try
        {  // System.out.println(sub);
            String insertCommand = "insert into QUESTION_CLASS_SUBJECT values(?,QUESTION_ID_SEQ.CURRVAL)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,sub);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
         
    }*/
     
    
     
     public ArrayList<QuestionPaper> getQuestionPaper(String sub )
     { 
         ArrayList<QuestionPaper> questionPapers = new ArrayList<QuestionPaper>();
        try
        {  // System.out.println(sub);
            String insertCommand = "select  q_no,duration,total_marks\n" +
                                   "from QUESTIONPAPER\n" +
                                   "where q_no in(select distinct q_no from(\n" +
                                   "select q_no from QUESTIONPAPER_QUESTION\n" +
                                   "where q_no in(select q_no from QUESTIONPAPER)\n" +
                                   "and q_id in(select q_id from QUESTION_CLASS_SUBJECT\n" +
                                   "where SUBJECT_ID = ?)))";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1,sub);
             ResultSet rs = stmt.executeQuery();
          
            while(rs.next())
            {
                //String accountno = rs.getString("");
                int qNo = rs.getInt("q_no");
                int duration = rs.getInt("duration");
                int totalMarks = rs.getInt("total_marks");
                QuestionPaper questionPaper = new QuestionPaper(qNo, duration, totalMarks);
                questionPapers.add(questionPaper);
               // System.out.println("jkkkkkkkkkkkkkkk");
            }
        
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        return questionPapers;
        
         
    }
     public void setQuesIntoPaper( int q_id)
     { 
        try
        {  
            String insertCommand = "insert into  QUESTIONPAPER_QUESTION values(?,QUESTIONPAPER_NO_SEQ.CURRVAL)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,q_id);
            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
         
    }
     public ArrayList<Question> getQuestions(int tId,String sub)
    {
        ArrayList<Question> questions = new ArrayList<Question>();
        
        String selectStatement = "select Q1.question, Q1.q_id from QUESTION Q1 JOIN QUESTION_CLASS_SUBJECT Q2 ON (Q1.q_id=Q2.q_id) where Q2.subject_id = ? order by Q1.q_id asc";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1, sub);
            ResultSet rs = stmt.executeQuery();
          
            while(rs.next())
            {
                //String accountno = rs.getString("");
                int qId = rs.getInt("q_id");
                String question = rs.getString("question");
                Question ques = new Question(qId, tId, question);
                questions.add(ques);
               // System.out.println("jkkkkkkkkkkkkkkk");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return questions;

    }
     
     
     public void setQuestionPaper(int duration, int totalMarks)
     { 
        try
        {  // System.out.println(sub);
            String insertCommand = "insert into QUESTIONPAPER values(QUESTIONPAPER_NO_SEQ.NEXTVAL,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,duration);
            stmt.setInt(2,totalMarks);
        
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
         
    }
     
     
     public void setExamPaper( int q_no)
     { 
        try
        {  
            String insertCommand = "insert into  EXAMQUESTION_QUESTIONPAPER values(?,SYSDATE)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,q_no);
            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
         
    }
     
     public void setPracticePaper( int q_no)
     { 
        try
        {  
            String insertCommand = "insert into PRACTICEQUESTION_QUESTIONPAPER values(?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,q_no);
            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
         
    }
     
     public ArrayList<Question> getQuestionForPaper(int qNo,int tId)
    {
        ArrayList<Question> questions = new ArrayList<Question>();
        
        String selectStatement = "select Q1.question, Q1.q_id from QUESTION Q1 JOIN QUESTIONPAPER_QUESTION Q2 ON (Q1.q_id=Q2.q_id) where Q2.q_no = ?";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1, qNo);
            ResultSet rs = stmt.executeQuery();
          
            while(rs.next())
            {
                //String accountno = rs.getString("");
                int qId = rs.getInt("q_id");
                String question = rs.getString("question");
                Question ques = new Question(qId, tId, question);
                questions.add(ques);
               // System.out.println("jkkkkkkkkkkkkkkk");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return questions;

    }
     
     
     public   ArrayList<Answer> getAnswers(String subject , int sId)
    {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        //String selectStatement = "select question from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)  and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        //String selectStatement1 = "select option1,option2,option3,option4 from MCQ_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ?)) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) and q_id in (select q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from QUESTIONPAPER) and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))";
        String selectStatement =" select s_id, q_id, q_no,answer from STUDENT_ANSWERS_QUESTION where q_id in (select q_id from QUESTION where t_id = (SELECT t_id from subject where class_id = ? and subject_id = ? ) and q_id IN (select Q_ID from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?) "+
           " and q_id in( SELECT q_id from QUESTIONPAPER_QUESTION where q_no = (select max(q_no) from(select q_no from QUESTIONPAPER_QUESTION "+ 
           " where q_no in(select q_no from EXAMQUESTION_QUESTIONPAPER )and q_id in(select q_id from QUESTION_CLASS_SUBJECT where SUBJECT_ID = ?))))) "+
           " and q_id in(select q_id from DESCRIPTIVE_QUESTION) and s_id = ? order by q_id asc " ;
       
        int counter = 1;
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
           
            
            int i = subject.length();
            int j = subject.charAt(i-1) - 48;
            //int j = Integer.parseInt(subject.substring(subject.length()-2,subject.length()-1));
            stmt.setInt(1,j);
            stmt.setString(2,subject);
            stmt.setString(3,subject);
            stmt.setString(4,subject);
            stmt.setInt(5,sId);
            System.out.println(subject);
            System.out.println(j);
            ResultSet rs = stmt.executeQuery();
          
            while(rs.next())
            {
                //String accountno = rs.getString("");
                int qId = rs.getInt("q_id");
                int qNo = rs.getInt("q_no");
                String answer = rs.getString("answer");
               Answer ans = new Answer(sId, qId,qNo, answer);
                answers.add(ans);
               // System.out.println("jkkkkkkkkkkkkkkk");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return answers;

            
            
    }
     
      public ArrayList<Integer> getStudentId1(String sub)
    {
        ArrayList<Integer> students = new ArrayList<Integer>();
        
        String selectStatement = "select s_id from student s1 join subject s2  on s1.class_id=s2.class_id where subject_id= ? order by s_id asc ";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1, sub);
            ResultSet rs = stmt.executeQuery();
          
            while(rs.next())
            {
                //String accountno = rs.getString("");
                int sId = rs.getInt("s_id");
                
                students.add(sId);
               // System.out.println("jkkkkkkkkkkkkkkk");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return students;

    }
     
     public void updateMarks( int s_id , int q_id, int q_no, int marks)
     { 
        try
        {  
            String insertCommand = "UPDATE STUDENT_ANSWERS_QUESTION\n" +
                                 "SET marks = ?\n" +
                                 "WHERE s_id = ? and q_id = ? and q_no = ? " ;
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1,marks);
            stmt.setInt(2,s_id);
            stmt.setInt(3,q_id);
            stmt.setInt(4,q_no);
            stmt.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
         
    }
    
/*
//Call function sample:
    
    String sql = "{ ? = call FUNCT_PERSON(?,?) }";
    CallableStatement statement = connection.prepareCall(sql);
    statement.setString(2,username);
    statement.setString(3,password);
    statement.registerOutParameter(1, java.sql.Types.INTEGER);  

    statement.execute();   
    //this is the main line
    long id = statement.getLong(1);
    if (id > 0) {
        //proceed to another page
    } else {
        //Go back to the login page
    }*/
    
 /*
    String sql = "{ call PROC_PERSON(?,?,?) }";
    CallableStatement statement = connection.prepareCall(sql);
    statement.setString(2,username);
    statement.setString(3,password);
    statement.registerOutParameter(1, java.sql.Types.INTEGER);  

    statement.execute();   
    //this is the main line
    long id = statement.getLong(1);
    if (id > 0) {
        //proceed to another page
    } else {
        //Go back to the login page
    }*/ 
    
    public static void main(String[] args)
    {
        DataAccess db = new DataAccess();
        System.out.println(db.isQuestionAvailable(1049,"PHY09"));
        //db.studentAnswerPerQuestion();
        // PreparedStatement stmt = conn.prepareStatement(selectStatement);
    }
  
}
