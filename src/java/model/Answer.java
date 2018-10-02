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
public class Answer {
    
    int qId;
    int qNo;
    int sId;
    String answer;
    public Answer(int sId,int qId,int qNo, String answer) {
        this.sId = sId;
        this.qId = qId;
        this.qNo = qNo;
        this.answer=answer;
        
    }

    public int getQId() {
        return qId;
    }

    public void setQId(int qId) {
        this.qId = qId;
    }

    public int getSId() {
        return sId;
    }

    public void setQNo(int qNo) {
        this.qNo = qNo;
    }
     public int getQNo() {
        return qNo;
    }

    public void setSId(int sId) {
        this.sId = sId;
    }
     public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
