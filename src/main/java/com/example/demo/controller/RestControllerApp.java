package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmpRepo;
@CrossOrigin
@RestController
public class RestControllerApp {

	@Autowired
	EmpRepo repo;
	
	@PostMapping(value="/addEmp")
	public String addEmpl(@RequestBody Employee e)
	{
		Object ob=repo.save(e);
		return ob!=null?"Employee Added Success ..":"Something Wrong";
	}
	@GetMapping("/view")
	public List<Employee> getList()
	{
		List<Employee> l=repo.findAll();
		return l;
	}
	@GetMapping("/del/{eid}")
	public String DelEmp(@PathVariable("eid") String id)
	{
		boolean b=false;
		List<Employee> l=repo.findAll();
		for(Employee e:l)
		{
			if(e.getName().equals(id))
			{
				repo.delete(e);
				b=true;
			}
		}
		if(b)
		{
			return "Employee Deleted Success";
		}
		else
		{
			return "Something Wrong..";
		}	
	}
	
	@GetMapping("/search/{name}")
	public Employee searchEmp(@PathVariable("name")String name)
	{
		Employee l2=null;
		List<Employee> l=repo.findAll();
		for(Employee e:l)
		{
			if(e.getName().equalsIgnoreCase(name))
			{
				Optional<Employee> l1=repo.findById(e.getId());
				 l2=l1.get();
			}
		}
		return l2;
	}
}
