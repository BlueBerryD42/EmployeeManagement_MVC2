/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBUtils.MyConnection;
import dto.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class SkillDAO {

    public int insertSkill(String name, String note) {
        int kq = 0;
        Connection conn = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "insert dbo.Skill(SkillName, Note) values (?, ?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, name);
                st.setString(2, note);
                kq = st.executeUpdate();
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

    public ArrayList<Skill> getSkills() {
        ArrayList<Skill> kq = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnection.getConnection();
            if (conn != null) {
                String sql = "select [SkillNo], [SkillName], [Note] from [dbo].[Skill]";
                Statement st = conn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int id = table.getInt("SkillNo");
                        String name = table.getString("SkillName");
                        String note = table.getString("Note");
                        Skill s = new Skill(id, name, note);
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

    public int updateSkill(int id, String name, String note) {
        int kq = 0;
        Connection cn = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "  update [dbo].[Skill]\n"
                        + "  set [SkillName]=?, [Note]=?\n"
                        + "  where [SkillNo]=?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, name);
                st.setString(2, note);
                st.setInt(3, id);
                kq = st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close(); //buoc 4
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return kq;
    }
}
