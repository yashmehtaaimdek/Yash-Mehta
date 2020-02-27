package com.aimdek.springExample.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aimdek.springExample.dao.EmployeeDAO;
import com.aimdek.springExample.model.Employee;

@Controller
public class HomeController {

	@Autowired
    private EmployeeDAO employeeDAO;
	
 
	@RequestMapping(value="/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException{
	    List<Employee> listEmployee = employeeDAO.list();
	    model.addObject("listEmployee", listEmployee);
	    model.setViewName("home");
	 
	    return model;
	}
	
	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newEmployee(ModelAndView model) {
	    Employee newEmployee = new Employee();
	    model.addObject("employee", newEmployee);
	    model.setViewName("EmployeeForm");
	    return model;
	}
	
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
	    employeeDAO.saveOrUpdate(employee);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
	    int employeeId = Integer.parseInt(request.getParameter("id"));
	    employeeDAO.delete(employeeId);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request) {
	    int employeeId = Integer.parseInt(request.getParameter("id"));
	    Employee employee = employeeDAO.get(employeeId);
	    ModelAndView model = new ModelAndView("EmployeeForm");
	    model.addObject("employee", employee);
	 
	    return model;
	}
	
}
