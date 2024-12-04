/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Employee {

    private int no;
    private String name;
    private Date bday;
    private int depNo;
    private int mgrNo;
    private Date startDate;
    private float salary;
    private int status;
    private String note;
    private int level;
    private String deptName;
    private ArrayList skills;

    public Employee() {
    }

    public Employee(int no, String name, Date bday, int depNo, int mgrNo, Date startDate, float salary, int status, String note, int level, String deptName, ArrayList skills) {
        this.no = no;
        this.name = name;
        this.bday = bday;
        this.depNo = depNo;
        this.mgrNo = mgrNo;
        this.startDate = startDate;
        this.salary = salary;
        this.status = status;
        this.note = note;
        this.level = level;
        this.deptName = deptName;
        this.skills = skills;
    }

    public ArrayList getSkills() {
        return skills;
    }

    public void setSkills(ArrayList skills) {
        this.skills = skills;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public int getDepNo() {
        return depNo;
    }

    public void setDepNo(int depNo) {
        this.depNo = depNo;
    }

    public int getMgrNo() {
        return mgrNo;
    }

    public void setMgrNo(int mgrNo) {
        this.mgrNo = mgrNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
