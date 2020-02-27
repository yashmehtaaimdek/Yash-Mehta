package com.aimdek.springExample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.aimdek.springExample.model.Employee;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public EmployeeDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveOrUpdate(Employee employee) {
		if (employee.getId() > 0) {
			// update
	        String sql = "UPDATE employee SET name=?, email=?, address=?, phone=? WHERE employee_id=?";
	        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getAddress(), employee.getPhone(), employee.getId());
	    } else {
	        // insert
	        String sql = "INSERT INTO employee (name, email, address, phone) VALUES (?, ?, ?, ?)";
	        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getAddress(), employee.getPhone());
	    }
		
	}

	@Override
	public void delete(int employeeId) {
		String sql = "DELETE FROM employee WHERE employee_id=?";
	    jdbcTemplate.update(sql, employeeId);
	}

	@Override
	public Employee get(int employeeId) {
		String sql = "SELECT * FROM employee WHERE employee_id=" + employeeId;
	    return jdbcTemplate.query(sql, new ResultSetExtractor<Employee>() {
	 
	        @Override
	        public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
	            if (rs.next()) {
	                Employee employee = new Employee();
	                employee.setId(rs.getInt("employee_id"));
	                employee.setName(rs.getString("name"));
	                employee.setEmail(rs.getString("email"));
	                employee.setAddress(rs.getString("address"));
	                employee.setPhone(rs.getString("phone"));
	                return employee;
	            }
	            return null;
	        }
	 
	    });
	}

	@Override
	public List<Employee> list() {
		String sql = "SELECT * FROM employee";
	    List<Employee> listEmployee = jdbcTemplate.query(sql, new RowMapper<Employee>() {
	 
	        @Override
	        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Employee aEmployee = new Employee();
	 
	            aEmployee.setId(rs.getInt("employee_id"));
	            aEmployee.setName(rs.getString("name"));
	            aEmployee.setEmail(rs.getString("email"));
	            aEmployee.setAddress(rs.getString("address"));
	            aEmployee.setPhone(rs.getString("phone"));
	 
	            return aEmployee;
	        }
	 
	    });
	 
	    return listEmployee;
	}
}
