package com.aimdek.javacrud.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aimdek.javacrud.model.Employee;
import com.aimdek.javacrud.utility.DatabaseConnection;



public class EmployeePersistenceImpl implements EmployeePersistence {
	
	private Connection con = null;
	public Statement stmt = null;
	
	protected void connect() throws SQLException, ClassNotFoundException {
		try {
			con = DatabaseConnection.getInstance().getConnection();
			System.out.println("Inside connect()");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
    }

	@Override
	public boolean insertEmployee(Employee employee){
		try {
			String sql = "INSERT INTO employee (name, email, address, phone) VALUES(?, ?, ?, ?)";
			connect();
	        
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, employee.getName());
	        statement.setString(2, employee.getEmail());
	        statement.setString(3, employee.getAddress());
	        statement.setString(4, employee.getPhone());
	        
	        boolean rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	        //disconnect();
	        return rowInserted;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<Employee> listAllEmployee() {
		try {
			List<Employee> listEmployee = new ArrayList<>();
			String sql = "SELECT * FROM employee";
			System.out.println("Inside List All Employee");
	        
	        connect();
	         
	        Statement statement = con.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        System.out.println("Below Result Statement");
	        
	        while (resultSet.next()) {
	            int id = resultSet.getInt("employee_id");
	            String name = resultSet.getString("name");
	            String email = resultSet.getString("email");
	            String address = resultSet.getString("address");
	            String phone = resultSet.getString("phone");
	             
	            Employee employee = new Employee(id, name, email, address, phone);
	            listEmployee.add(employee);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        //disconnect();
			return listEmployee;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		try {
			String sql = "DELETE FROM employee where employee_id = ?";
			connect();
	        
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, employee.getId());
	        
	        boolean rowDeleted = statement.executeUpdate() > 0;
	        statement.close();
	        //disconnect();
	        return rowDeleted;  
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		try {
			String sql = "UPDATE employee SET name = ?, email = ?, address = ?, phone = ? WHERE employee_id = ?";
	        connect();
	         
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, employee.getName());
	        statement.setString(2, employee.getEmail());
	        statement.setString(3, employee.getAddress());
	        statement.setString(4, employee.getPhone());
	        statement.setInt(5, employee.getId());
	         
	        boolean rowUpdated = statement.executeUpdate() > 0;
	        statement.close();
	        //disconnect();
	        
	        return rowUpdated;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Employee getEmployee(int id) {
		try {
			Employee employee = null;
	        String sql = "SELECT * FROM employee WHERE employee_id = ?";
	         
	        connect();
	         
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, id);
	         
	        ResultSet resultSet = statement.executeQuery();
	         
	        if (resultSet.next()) {
	            String name = resultSet.getString("name");
	            String email = resultSet.getString("email");
	            String address = resultSet.getString("address");
	            String phone = resultSet.getString("phone");
	             
	            employee = new Employee(id, name, email, address, phone);
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return employee;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
