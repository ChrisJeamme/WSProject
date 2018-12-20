package com.projetws.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetws.model.Country;
import com.projetws.model.Employee;
import com.projetws.model.EmployeeDTO;
import com.projetws.model.EmployeeRepository;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/employee")
@Api(value = "employees")
public class EmployeeController
{
	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/firstName/{firstName}", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDTO> getByFirstName(@PathVariable String firstName)
	{
		ModelMapper mapper = new ModelMapper();
		
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
		List<Employee> employeeList = employeeRepository.findAll();
		
		for(Employee e : employeeList)
		{
			if(e.getFirstName().equals(firstName))
				employeeDTOList.add(mapper.map(e,  EmployeeDTO.class));
		}
		
		return employeeDTOList;
	}
	
	@RequestMapping(value = "/salaryDistribution", method = RequestMethod.GET)
	public String getsalaryDistributionPage(Model m)
	{
		List<Employee> employees = employeeRepository.findByOrderBySalary();
		m.addAttribute("employees", employees);
		return "salary_distribution";
	}
}
