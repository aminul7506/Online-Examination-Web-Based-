/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class Question {
    int qId;
    int tId;
    String question;
    public Question(int qId,int tId, String question) {
        this.qId = qId;
        this.tId = tId;
        this.question=question;
        
    }

    public int getQId() {
        return qId;
    }

    public void setQId(int qId) {
        this.qId = qId;
    }

    public int getTID() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }
     public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
}
/*public class MCQ_Question extends Question {
    int qId;
    int tId;

    public MCQ_Question(int qId,int tId) {
        this.qId = qId;
        this.tId = tId;
        
    }

    public int getQId() {
        return qId;
    }

    public void setQId(int classId) {
        this.qId = qId;
    }

    public int getTID() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }
    
}*/

