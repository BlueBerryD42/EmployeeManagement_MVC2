/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mainController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class MainController extends HttpServlet {

    private static final String WELCOME = "index.html";

    private static final String SKILL = "ViewSkill";
    private static final String SKILL_SERVLET = "getSkillServlet";

    private static final String UPDATE_SKILL = "UPDATE_SKILL";
    private static final String UPDATE_SKILL_SERVLET = "UpdateSkillPage.jsp";

    private static final String SAVE_SKILL = "SAVE_SKILL";
    private static final String SAVE_SKILL_SERVLET = "updateSkillServlet";

    private static final String CREATE_SKILL = "CREATE_SKILL";
    private static final String CREATE_SKILL_SERVLET = "createSkillServlet";

    private static final String DEPARTMENT = "ViewDepartment";
    private static final String DEPARTMENT_SERVLET = "getDepartmentServlet";

    private static final String UPDATE_DEPARTMENT = "UPDATE_DEPARTMENT";
    private static final String UPDATE_DEPARTMENT_SERVLET = "updateDepartmentServlet";

    private static final String CREATE_DEPARTMENT = "CREATE_DEPARTMENT";
    private static final String CREATE_DEPARTMENT_SERVLET = "createDepartmentServlet";

    private static final String EMPLOYEE = "ViewEmployee";
    private static final String EMPLOYEE_SERVLET = "getEmployeeServlet";

    private static final String UPDATE_EMPLOYEE = "UPDATE_EMPLOYEE_STATUS";
    private static final String UPDATE_EMPLOYEE_SERVLET = "deleteEmployeeServlet";

    private static final String CREATE_EMPLOYEE = "CREATE_EMPLOYEE";
    private static final String CREATE_EMPLOYEE_SERVLET = "createEmployeeServlet";

    private static final String DELETE_EMPLOYEE = "UPDATE_EMPLOYEE_STATUS";
    private static final String DELETE_EMPLOYEE_SERVLET = "deleteEmployeeServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            //Action is the data being sent between each page to do a function
            //Action will be null when the project first initiallized
            String action = request.getParameter("action");
            if (SKILL.equals(action)) {
                url = SKILL_SERVLET;
            } else if (UPDATE_SKILL.equals(action)) {
                url = UPDATE_SKILL_SERVLET;
            } else if (SAVE_SKILL.equals(action)) {
                url = SAVE_SKILL_SERVLET;
            } else if (CREATE_SKILL.equals(action)) {
                url = CREATE_SKILL_SERVLET;
            } else if (DEPARTMENT.equals(action)) {
                url = DEPARTMENT_SERVLET;
            } else if (UPDATE_DEPARTMENT.equals(action)) {
                url = UPDATE_DEPARTMENT_SERVLET;
            } else if (CREATE_DEPARTMENT.equals(action)) {
                url = CREATE_DEPARTMENT_SERVLET;
            } else if (EMPLOYEE.equals(action)) {
                url = EMPLOYEE_SERVLET;
            } else if (DELETE_EMPLOYEE.equals(action)) {
                url = (DELETE_EMPLOYEE_SERVLET);
            } else if (CREATE_EMPLOYEE.equals(action)) {
                url = (CREATE_EMPLOYEE_SERVLET);
            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
