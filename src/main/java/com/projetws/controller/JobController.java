package com.projetws.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetws.model.Job;
import com.projetws.model.JobRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;

@Controller
@RequestMapping("/job")
@Api(value="job")
public class JobController
{
	@Autowired
    private JobRepository jobRepository;

	@ApiOperation(value = "Give the list of all jobs", response = List.class)
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public List<Job> list()
    {
        return jobRepository.findAll();
    }

	@ApiOperation(value = "Update a job", response = ResponseEntity.class)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable String id, @RequestBody Job job)
	{
		Job j = jobRepository.findByJobId(id);
		j.setJobTitle(job.getJobTitle());
		jobRepository.save(j);
		return new ResponseEntity("Job updated successfuly", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Give jobs above a minimum salary", response = List.class)
	@RequestMapping(value = "/jobAbove/{minimumSalary}", method = RequestMethod.GET)
	public List<Job> jobAbove(@PathVariable BigDecimal minimumSalary)
	{
		return jobRepository.findAllByMinSalaryGreaterThanOrderByMaxSalaryDesc(minimumSalary);
	}
 
}