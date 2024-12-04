/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBUtils.MyConnection;
import dto.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class DepartmentDAO {

    private static final String CREATE = "insert [dbo].[Department]([DeptName], [Note]) values (?, ?)";

    private static final String UPDATE = "update [dbo].[Department] set [DeptName] = ?, [Note] = ? where [DeptNo] = ?";

    //Fetch all department
    public ArrayList<Department> getDepartment() {
        ArrayList<Department> kq = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "  select [DeptNo], [DeptName], [Note] from [dbo].[Department]";
                Statement st = conn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int id = table.getInt("DeptNo");
                        String name = table.getString("DeptName");
                        String note = table.getString("Note");
                        Department s = new Department(id, name, note);
                        kq.add(s);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return kq;
    }

    //Create new department
    public int addDepartment(String name, String note) throws SQLException {
        int kq = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.MyConnection.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, name);
                ptm.setString(2, note);
                kq = ptm.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return kq;
    }

    public int updateDepartment(int id, String name, String note) throws SQLException {
        int kq = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.MyConnection.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, name);
                ptm.setString(2, note);
                ptm.setInt(3, id);
                kq = ptm.executeUpdate();
            }

        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return kq;
    }
}
