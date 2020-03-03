package com.aimdek.javacrud.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aimdek.javacrud.persistence.EmployeePersistence;
import com.aimdek.javacrud.persistence.EmployeePersistenceImpl;
import com.aimdek.javacrud.model.Employee;
/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeePersistence employeePersistence = new EmployeePersistenceImpl() ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertEmployee(request, response);
                break;
            case "/delete":
                deleteEmployee(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateEmployee(request, response);
                break;
            default:
                listEmployee(request, response);
                break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException  {
		List<Employee> listEmployee = employeePersistence.listAllEmployee();
	    request.setAttribute("listEmployee", listEmployee);
	    System.out.println("Above Set Attribute");
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeList.jsp");
        System.out.println("Below Set attribute");
	    dispatcher.forward(request, response);
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String address = request.getParameter("address");
	    String phone = request.getParameter("phone");
	 
	    Employee employee = new Employee(id, name, email, address, phone);
        employeePersistence.updateEmployee(employee);
        response.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = employeePersistence.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
		
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		 
        Employee employee = new Employee(id);
        employeePersistence.deleteEmployee(employee);
        response.sendRedirect("list");
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

 
        Employee newEmployee = new Employee(name, email, address, phone);
        employeePersistence.insertEmployee(newEmployee);
        response.sendRedirect("list");
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		System.out.println("Inside Show new form");
		RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
