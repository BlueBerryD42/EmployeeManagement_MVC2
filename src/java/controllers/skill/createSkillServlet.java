/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.skill;

import dao.SkillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class createSkillServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("skillName");
            String note = request.getParameter("note");
            if (name != null && note != null && name.length() <= 30 && note.length() <= 100) {
                //Save to DB then throw success mssg
                SkillDAO d = new SkillDAO();
                int kq = d.insertSkill(name, note);
                if (kq == 1) {
//                    request.setAttribute("Mssg", "New skil created successfull!!");
//                    request.getRequestDispatcher("SkillManagementPage.jsp").forward(request, response);
                    request.getRequestDispatcher("MainController?action=ViewSkill").forward(request, response);
                } else {
                    request.setAttribute("Mssg", "Invalid data input");
                    request.getRequestDispatcher("SkillManagementPage.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("Mssg", "Invalid data input");
                request.getRequestDispatcher("SkillManagementPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
