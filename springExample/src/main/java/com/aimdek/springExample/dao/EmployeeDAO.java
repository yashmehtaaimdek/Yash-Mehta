package com.aimdek.springExample.dao;

import com.aimdek.springExample.model.Employee;

import java.util.List;

public interface EmployeeDAO {
	
	public void saveOrUpdate(Employee employee);
	public void delete(int employeeId);
	public Employee get(int employeeId);
	public List<Employee> list();

}
