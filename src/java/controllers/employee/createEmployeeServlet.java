/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.employee;

import dao.EmployeeDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class createEmployeeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String name = request.getParameter("name");
            String bdayStr = request.getParameter("bday");
            String depNo = request.getParameter("deptNo");
            String mgrNo = request.getParameter("mgrNo");
            String startDateStr = request.getParameter("startDate");
            String salary = request.getParameter("salary");
            String status = request.getParameter("status");
            String note = request.getParameter("note");
            String level = request.getParameter("level");
            String[] skillIds = request.getParameterValues("skills"); // Get skill IDs as an array

            if (name != null && bdayStr != null && depNo != null && mgrNo != null
                    && startDateStr != null && status != null && salary != null && note != null
                    && level != null && skillIds != null) {

                EmployeeDAO dao = new EmployeeDAO();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date startDate = new java.sql.Date(sdf.parse(startDateStr).getTime());
                java.sql.Date bday = new java.sql.Date(sdf.parse(bdayStr).getTime());

                int depNoInt = Integer.parseInt(depNo);
                int mgrNoInt = Integer.parseInt(mgrNo);
                float salaryFloat = Float.parseFloat(salary);
                int statusInt = Integer.parseInt(status);
                int levelInt = Integer.parseInt(level);

                int employeeId = dao.addEmployee(name, bday, depNoInt, mgrNoInt, startDate, salaryFloat, statusInt, note, levelInt);

                if (employeeId > 0) {
                    boolean skillsAdded = dao.addEmployeeSkills(employeeId, skillIds);
                    if (skillsAdded) {
                        request.getRequestDispatcher("MainController?action=ViewEmployee").forward(request, response);
                    } else {
                        request.setAttribute("Mssg", "Error adding skills to the new employee");
                        request.getRequestDispatcher("EmployeeManagementPage.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("Mssg", "Error creating new employee");
                    request.getRequestDispatcher("EmployeeManagementPage.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("Mssg", "Invalid data input");
                request.getRequestDispatcher("EmployeeManagementPage.jsp").forward(request, response);
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
