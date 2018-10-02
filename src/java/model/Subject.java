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
public class Subject {
    int classId;
    int tId;
    String subId;

    public Subject(int classId,int tId, String subId) {
        this.classId = classId;
        this.tId = tId;
        this.subId = subId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTID() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }
    
    
}
