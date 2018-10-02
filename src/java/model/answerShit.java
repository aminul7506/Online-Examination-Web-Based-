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
public class answerShit {
     public List<String> getAnswer(HttpServletRequest request){
         List<String> answer = new ArrayList<String>();
          String type1 = request.getParameter("type1");
          String type2 = request.getParameter("type2");
          String type3 = request.getParameter("type3");
          String type4 = request.getParameter("type4");
          String type5 = request.getParameter("type5");
          String type6 = request.getParameter("type6");
          String type7 = request.getParameter("type7");
          String type8 = request.getParameter("type8");
          String type9 = request.getParameter("type9");
          String type10 = request.getParameter("type10");
          
           if(type1 != null){
                System.out.println(type1.charAt(0));
                answer.add(String.valueOf(type1.charAt(0)));
         }
           else{
               answer.add("f");
               System.out.println("f");
           }
         
         if(type2 != null){
                System.out.println(type2.charAt(0));
                answer.add(String.valueOf(type2.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
          
         if(type3 != null){
                System.out.println(type3.charAt(0));
                answer.add(String.valueOf(type3.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(type4 != null){
                System.out.println(type4.charAt(0));
                answer.add(String.valueOf(type4.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(type5 != null){
                System.out.println(type5.charAt(0));
                answer.add(String.valueOf(type5.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(type6 != null){
                System.out.println(type6.charAt(0));
                answer.add(String.valueOf(type6.charAt(0)));
         }
         else{
             answer.add("f");
              System.out.println("f");
         }
          
         if(type7 != null){
                System.out.println(type7.charAt(0));
                answer.add(String.valueOf(type7.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(type8 != null){
                System.out.println(type8.charAt(0));
                answer.add(String.valueOf(type8.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(type9 != null){
                System.out.println(type9.charAt(0));
                answer.add(String.valueOf(type9.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
         
         if(type10 != null){
                System.out.println(type10.charAt(0));
                answer.add(String.valueOf(type10.charAt(0)));
         }
         else {
             answer.add("f");
              System.out.println("f");
         }
   /*   for(int i = 1; i <= quantity; i++){
          if(i == 1){
          if(type1.equals("a1"))answer.add("a");
          else if(type1.equals("b1"))answer.add("b");
          else if(type1.equals("c1"))answer.add("c");
          else if(type1.equals("d1"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 2){
          if(type2.equals("a2"))answer.add("a");
          else if(type2.equals("b2"))answer.add("b");
          else if(type2.equals("c2"))answer.add("c");
          else if(type2.equals("d2"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 3){
          if(type3.equals("a3"))answer.add("a");
          else if(type3.equals("b3"))answer.add("b");
          else if(type3.equals("c3"))answer.add("c");
          else if(type3.equals("d3"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 4){
          if(type4.equals("a4"))answer.add("a");
          else if(type4.equals("b4"))answer.add("b");
          else if(type4.equals("c4"))answer.add("c");
          else if(type4.equals("d4"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 5){
          if(type5.equals("a5"))answer.add("a");
          else if(type5.equals("b5"))answer.add("b");
          else if(type5.equals("c5"))answer.add("c");
          else if(type5.equals("d5"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 6){
          if(type6.equals("a6"))answer.add("a");
          else if(type6.equals("b6"))answer.add("b");
          else if(type6.equals("c6"))answer.add("c");
          else if(type6.equals("d6"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 7){
          if(type7.equals("a7"))answer.add("a");
          else if(type7.equals("b7"))answer.add("b");
          else if(type7.equals("c7"))answer.add("c");
          else if(type7.equals("d7"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 8){
          if(type8.equals("a8"))answer.add("a");
          else if(type8.equals("b8"))answer.add("b");
          else if(type8.equals("c8"))answer.add("c");
          else if(type8.equals("d8"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 9){
          if(type9.equals("a9"))answer.add("a");
          else if(type9.equals("b9"))answer.add("b");
          else if(type9.equals("c9"))answer.add("c");
          else if(type9.equals("d9"))answer.add("d");
          else answer.add("f");
          }
          else if(i == 10){
          if(type10.equals("a10"))answer.add("a");
          else if(type10.equals("b10"))answer.add("b");
          else if(type10.equals("c10"))answer.add("c");
          else if(type10.equals("d10"))answer.add("d");
          else answer.add("f");
          }
      }*/
          return answer;
     }
}
