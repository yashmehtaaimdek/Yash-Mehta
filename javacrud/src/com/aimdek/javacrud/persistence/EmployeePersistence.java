package com.aimdek.javacrud.persistence;

import java.util.List;

import com.aimdek.javacrud.model.Employee;

public interface EmployeePersistence {
	public boolean insertEmployee(Employee employee);
	public List<Employee> listAllEmployee();
	public boolean deleteEmployee(Employee employee);
	public boolean updateEmployee(Employee employee);
	public Employee getEmployee(int id);
}
