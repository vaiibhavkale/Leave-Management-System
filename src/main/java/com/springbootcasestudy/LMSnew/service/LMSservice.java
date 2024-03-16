package com.springbootcasestudy.LMSnew.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbootcasestudy.LMSnew.model.Model;

@Repository
public interface LMSservice {
	
	public List<Model> getEmployeeData();
	
	public void updateEmployeeData(List<Model> emplist);
}
