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
public class QuestionPaper {
   int qNo;
    int duration;
    int totalMarks;
    public QuestionPaper(int qNo ,int duration, int totalMarks) {
        this.qNo = qNo;
        this.duration = duration;
        this.totalMarks=totalMarks;
        
    }

    public int getQNo() {
        return qNo;
    }

    public void setQNo(int qNo) {
        this.qNo = qNo;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration ) {
        this.duration = duration;
    }
     public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    } 
}
