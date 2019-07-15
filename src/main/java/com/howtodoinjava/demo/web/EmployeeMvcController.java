package com.howtodoinjava.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/")
public class EmployeeMvcController 
{
	@Autowired
	EmployeeService service;

	@RequestMapping
	public List<EmployeeEntity> getAllEmployees()
	{

		return service.getAllEmployees();
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public EmployeeEntity editEmployeeById(@RequestBody EmployeeEntity emp) throws RecordNotFoundException
	{
		if (emp.getId()!=null) {
			return service.getEmployeeById(emp.getId());
		} else {
			return new EmployeeEntity();
		}
	}
	
	@RequestMapping(path = "/delete")
	public String deleteEmployeeById(@RequestBody EmployeeEntity emp) throws RecordNotFoundException
	{
		service.deleteEmployeeById(emp.getId());
		return "ok";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public EmployeeEntity createOrUpdateEmployee(@RequestBody EmployeeEntity employee)
	{
		service.createOrUpdateEmployee(employee);
		return employee;
	}


}
