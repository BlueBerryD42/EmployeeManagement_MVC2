/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBUtils.MyConnection;
import dto.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class EmployeeDAO {

    private static final String GET_EMP
            = "SELECT [EmpNo], [EmpName], [BirthDay], [DeptNo], [MgrNo], [StartDate], [Salary], [EmpStatus], [Note], [EmpLevel] "
            + "FROM [EMS].[dbo].[Employee]";

    private static final String GET_DEP
            = "SELECT [DeptName] FROM [EMS].[dbo].[Department] WHERE [DeptNo] = ?";

    private static final String GET_SKILLS
            = "SELECT s.SkillName "
            + "FROM [EMS].[dbo].[Emp_Skill] es "
            + "JOIN [EMS].[dbo].[Skill] s ON es.SkillNo = s.SkillNo "
            + "WHERE es.EmpNo = ?";

    // Fetch all employees
    public ArrayList<Employee> getEmployee() {
        ArrayList<Employee> emp = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_EMP);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("EmpNo");
                    String name = rs.getString("EmpName");
                    Date bday = rs.getDate("BirthDay");
                    int depId = rs.getInt("DeptNo");
                    int mgrNo = rs.getInt("MgrNo");
                    Date startDate = rs.getDate("StartDate");
                    float salary = rs.getFloat("Salary");
                    int status = rs.getInt("EmpStatus");
                    String note = rs.getString("Note");
                    int level = rs.getInt("EmpLevel");

                    // Fetch department name separately
                    String deptName = getDepartmentName(depId, conn);

                    // Fetch skills for the employee
                    ArrayList<String> skills = getSkillsForEmployee(id, conn);

                    Employee s = new Employee(id, name, bday, depId, mgrNo, startDate, salary, status, note, level, deptName, skills);
                    emp.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ptm != null) {
                    ptm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    // Fetch department name for a given DeptNo
    private String getDepartmentName(int deptNo, Connection conn) {
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String deptName = null;
        try {
            ptm = conn.prepareStatement(GET_DEP);
            ptm.setInt(1, deptNo);
            rs = ptm.executeQuery();
            if (rs.next()) {
                deptName = rs.getString("DeptName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ptm != null) {
                    ptm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deptName;
    }

    // Fetch skill name for a given EmpNo
    private ArrayList<String> getSkillsForEmployee(int empNo, Connection conn) {
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ArrayList<String> skills = new ArrayList<>();
        try {
            ptm = conn.prepareStatement(GET_SKILLS);
            ptm.setInt(1, empNo);
            rs = ptm.executeQuery();
            while (rs.next()) {
                String skillName = rs.getString("SkillName");
                skills.add(skillName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ptm != null) {
                    ptm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return skills;
    }

}
