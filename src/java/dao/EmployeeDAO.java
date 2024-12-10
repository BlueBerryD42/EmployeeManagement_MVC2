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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class EmployeeDAO {

    private static final String GET_EMP
            = "SELECT [EmpNo], [EmpName], [BirthDay], [DeptNo], [MgrNo], [StartDate], [Salary], [EmpStatus], [Note], [EmpLevel] "
            + "FROM [EMS].[dbo].[Employee]";

    private static final String SEARCH_EMP = GET_EMP + "WHERE [EmpName] LIKE ?";

    private static final String GET_DEP
            = "SELECT [DeptName] FROM [EMS].[dbo].[Department] WHERE [DeptNo] = ?";

    private static final String GET_SKILLS
            = "SELECT s.SkillName "
            + "FROM [EMS].[dbo].[Emp_Skill] es "
            + "JOIN [EMS].[dbo].[Skill] s ON es.SkillNo = s.SkillNo "
            + "WHERE es.EmpNo = ?";

    private static final String UPDATE_EMP_STATUS = "Update [dbo].[Employee] set [EmpStatus] = ? where [EmpNo] = ?";

    private static final String CREATE_EMP = "  insert [dbo].[Employee] ( [EmpName],[BirthDay],[DeptNo],[MgrNo],[StartDate],[Salary],[EmpStatus],[Note],[EmpLevel] ) values (?,?,?,?,?,?,?,?,?)";

    private static final String CREATE_EMP_SKILL = "INSERT INTO [dbo].[Emp_Skill] (EmpNo, SkillNo, SkillLevel, RegDate) VALUES (?, ?, ?, ?)";

    // Fetch all employees
    public ArrayList<Employee> getEmployee(String search) {
        ArrayList<Employee> emp = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                if (search == null || search.trim().isEmpty()) {
                    ptm = conn.prepareStatement(GET_EMP);
                } else {
                    ptm = conn.prepareStatement(SEARCH_EMP);
                    ptm.setString(1, "%" + search + "%");
                }
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

    public int deactivateEmployee(int no, int current) throws ClassNotFoundException, SQLException {
        int kq = 0;

        try (Connection conn = DBUtils.MyConnection.getConnection();
                PreparedStatement ptm = conn.prepareStatement(UPDATE_EMP_STATUS)) {

            if (conn != null) {
                // Toggle the status: If current is 1 (Active), set to 0 (Inactive), and vice versa
                int newStatus = (current == 1) ? 0 : 1;
                ptm.setInt(1, newStatus);
                ptm.setInt(2, no);

                kq = ptm.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Error in deactivateEmployee: " + e.getMessage());
            throw e; // Re-throwing the exception for the caller to handle
        }

        return kq;
    }

    public int addEmployee(String name, Date bday, int depNo, int mgrNo, Date startDate, float salary, int status, String note, int level) throws ClassNotFoundException, SQLException {
        int employeeId = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.MyConnection.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_EMP, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setString(1, name);
                ptm.setDate(2, bday);
                ptm.setInt(3, depNo);
                ptm.setInt(4, mgrNo);
                ptm.setDate(5, startDate);
                ptm.setFloat(6, salary);
                ptm.setInt(7, status);
                ptm.setString(8, note);
                ptm.setInt(9, level);

                int rowsInserted = ptm.executeUpdate();
                if (rowsInserted > 0) {
                    rs = ptm.getGeneratedKeys();
                    if (rs.next()) {
                        employeeId = rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return employeeId;
    }

    public boolean addEmployeeSkills(int empNo, String[] skillIds) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean success = true;

        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ptm = conn.prepareStatement(CREATE_EMP_SKILL);
                for (String skillId : skillIds) {
                    ptm.setInt(1, empNo);
                    ptm.setInt(2, Integer.parseInt(skillId));
                    ptm.setInt(3, 0);
                    ptm.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                    ptm.addBatch();
                }
                ptm.executeBatch();
                conn.commit();
            }
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            success = false;
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        return success;
    }

}
