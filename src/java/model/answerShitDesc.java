/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sojal
 */
public class answerShitDesc {
    public List<String> getAnswerDesc(HttpServletRequest request){
         List<String> answer = new ArrayList<String>();
         String desc1 = request.getParameter("desc1");
         String desc2 = request.getParameter("desc2");
         String desc3 = request.getParameter("desc3");
         String desc4 = request.getParameter("desc4");
         String desc5 = request.getParameter("desc5");
         String desc6 = request.getParameter("desc6");
          if(desc1 != null){
                System.out.println(desc1);
                answer.add(desc1);
         }
           else{
               answer.add("f");
               System.out.println("f");
           }
         
         if(desc2 != null){
                System.out.println(desc2);
                answer.add(desc2);
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
          
         if(desc3 != null){
                System.out.println(desc3);
                answer.add(desc3);
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(desc4 != null){
                System.out.println(desc4);
                answer.add(desc4);
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(desc5 != null){
                System.out.println(desc5);
                answer.add(desc5);
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(desc6 != null){
                System.out.println(desc6);
                answer.add(desc6);
         }
         else{
             answer.add("f");
              System.out.println("f");
         }
         return answer;
    }
}
