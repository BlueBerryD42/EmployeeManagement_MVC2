/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.employee;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.SkillDAO;
import dto.Department;
import dto.Employee;
import dto.Skill;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class getEmployeeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String search = request.getParameter("search");

            EmployeeDAO empDAO = new EmployeeDAO();
            ArrayList<Employee> empList = empDAO.getEmployee(search);
            request.setAttribute("listEmployee", empList);

            DepartmentDAO depDAO = new DepartmentDAO();
            ArrayList<Department> depList = depDAO.getDepartment();
            request.getSession().setAttribute("listDepartments", depList);

            SkillDAO skillDAO = new SkillDAO();
            ArrayList<Skill> skillList = skillDAO.getSkills();
            request.getSession().setAttribute("listSkills", skillList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeManagementPage.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log("Error at get Employee servlet" + e.toString());
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
