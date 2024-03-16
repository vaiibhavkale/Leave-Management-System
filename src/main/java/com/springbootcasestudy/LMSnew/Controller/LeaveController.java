package com.springbootcasestudy.LMSnew.Controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootcasestudy.LMSnew.model.Model;
import com.springbootcasestudy.LMSnew.service.LMSservice;

@Controller
public class LeaveController {
	
	@Autowired
	private LMSservice service;
	
	public int employeeId = 0;
	public String employeename = "";
	public String start_date = "";
	public String end_date = "";
	int totalLeaves = 0; 
	public int totalcountofleavesRemaining = 0;
	
	Model obj = new Model();
	String[] datearr = (end_date).split("-");
	List<Model> list_Maintained = new ArrayList<>();
	List<Integer> totalList;
	
	//function to calculate leaves
		public int calculateLeaves(LocalDate startDate, LocalDate endDate) {
			int leaves = 0;
	        while (!startDate.isAfter(endDate)) {
	            if (startDate.getDayOfWeek() != DayOfWeek.SATURDAY && startDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
	                leaves++;
	            }
	            startDate = startDate.plusDays(1);
	        }
	        return leaves;
		}
	
	@RequestMapping("/takeLeavePage")
	public String getEmployee(@RequestParam("empid") String empid, @RequestParam("empname") String empname, 
			@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate) {
		
			employeeId = Integer.parseInt(empid);
			employeename = empname;
			start_date = startdate;
			end_date = enddate;
			
			System.out.println(employeeId + employeename + start_date + end_date);
			System.out.println(service.getEmployeeData().get(1).getEmpname());
			
			Map<Integer, String> map = new HashMap<>();
			map.put(service.getEmployeeData().get(1).getEmpid(), service.getEmployeeData().get(1).getEmpname());
			map.put(service.getEmployeeData().get(2).getEmpid(), service.getEmployeeData().get(2).getEmpname());
			
			
//------------------------------------------------------------------------------------------------------------
			
			//validation
			if(map.containsKey(employeeId)) {
				if(map.get(employeeId).equalsIgnoreCase(employeename)) {
					System.out.println("Welcome " + employeename + "!!!");

//------------------------------------------------------------------------------------------------------------
					//Pending leaves
					for(Model m: service.getEmployeeData()) {
						if(employeename.equals(m.getEmpname())) {
							totalLeaves = m.getTotal();
							break;
						}
					}
//-------------------------------------------------------------------------------------------------------------		
					
					//counting leaves
				//	System.out.println(start_date + " " + end_date);
					
					DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate sd = LocalDate.parse(start_date,formatter);// example start date
			        LocalDate ed = LocalDate.parse(end_date,formatter);
			        int leaves_wanted = calculateLeaves(sd, ed);
			        totalcountofleavesRemaining = totalLeaves - leaves_wanted;
//			        System.out.println("Total leaves wanted: " + leaves_wanted);
//					System.out.println("Total leaves remaining: " + totalcountofleavesRemaining);
					
//-------------------------------------------------------------------------------------------------------------
						
					//matching month of the leave
					Map<Integer, String> monthMatchMap = new HashMap<>();
					monthMatchMap.put(1,  "Jan");
					monthMatchMap.put(2,  "Feb");
					monthMatchMap.put(3,  "Mar");
					monthMatchMap.put(4,  "Apr");
					monthMatchMap.put(5,  "May");
					monthMatchMap.put(6,  "Jun");
					monthMatchMap.put(7,  "Jul");
					monthMatchMap.put(8,  "Aug");
					monthMatchMap.put(9,  "Sep");
					monthMatchMap.put(10, "Oct");
					monthMatchMap.put(11, "Nov");
					monthMatchMap.put(12, "Dec");
					
					String[] month_no_arr = end_date.split("-");
					int month_no = Integer.parseInt(month_no_arr[1]);
					String month_name = monthMatchMap.get(month_no);
					
//					if(monthMatchMap.containsKey(month_no)){
//						System.out.println("The month in which you are taking leave: " + month_name);
//					}
//------------------------------------------------------------------------------------------------------------
				
					//substracting leaves
					Map<String, Integer> substractLeavesMap = new LinkedHashMap<>();
					substractLeavesMap.put("Jan", 2);
					substractLeavesMap.put("Feb", 2);
					substractLeavesMap.put("Mar", 2);
					substractLeavesMap.put("Apr", 2);
					substractLeavesMap.put("May", 2);
					substractLeavesMap.put("Jun", 2);
					substractLeavesMap.put("Jul", 2);
					substractLeavesMap.put("Aug", 2);
					substractLeavesMap.put("Sep", 2);
					substractLeavesMap.put("Oct", 2);
					substractLeavesMap.put("Nov", 2);
					substractLeavesMap.put("Dec", 2);
					
					String[] months_arr = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
					int startIndex = Arrays.asList(months_arr).indexOf("Jan");
					int endIndex = Arrays.asList(months_arr).indexOf(month_name);
					int totalLeavesCanGrant = 0;
					
					for (int i = startIndex; i <= endIndex; i++) {
					    String key = months_arr[i];
					    int value = substractLeavesMap.get(key);
					//    System.out.println(key + " : " + value);
					    totalLeavesCanGrant += value;
					   
					}
					
				//	System.out.println("Total Leaves granted are: " + totalLeavesCanGrant);
					
					if(totalLeavesCanGrant < leaves_wanted) {
						System.out.println("You have only " + totalLeavesCanGrant + " balance leaves. You can't take more than that.");
						
					}else {
						
						int temp_leaves = leaves_wanted;
						
						System.out.println("Leaves Successfully granted.");
						
						if(totalLeavesCanGrant >= leaves_wanted) {
							
							for(int i =endIndex; i >= startIndex; i--) {
								String key = months_arr[i];
								int value = substractLeavesMap.get(key);
								
								temp_leaves = temp_leaves - value;
								if(temp_leaves != 0) {
									substractLeavesMap.put(key, 0);
								}
								if(temp_leaves == 1) {
									substractLeavesMap.put(key, 1);
								}
								
						//		System.out.println(key + ": " + value);
								
								//counting total no. of leaves pending
//								System.out.println(list_Maintained);
							}
							
					//		System.out.println(substractLeavesMap);
							
						
							for (Model emp : service.getEmployeeData()) {
								emp.setEmpid(employeeId);
								emp.setEmpname(employeename);
							    emp.setJan(substractLeavesMap.get("Jan"));
							    emp.setFeb(substractLeavesMap.get("Feb"));
							    emp.setMar(substractLeavesMap.get("Mar"));
							    emp.setApr(substractLeavesMap.get("Apr"));
							    emp.setMay(substractLeavesMap.get("May"));
							    emp.setJun(substractLeavesMap.get("Jun"));
							    emp.setJul(substractLeavesMap.get("Jul"));
							    emp.setAug(substractLeavesMap.get("Aug"));
							    emp.setSep(substractLeavesMap.get("Sep"));
							    emp.setOct(substractLeavesMap.get("Oct"));
							    emp.setNov(substractLeavesMap.get("Nov"));
							    emp.setDec(substractLeavesMap.get("Dec"));
							    emp.setTotal(totalcountofleavesRemaining);
							    
							    list_Maintained.add(emp);
		
							    // update the database with the updated model object values
							    service.updateEmployeeData(list_Maintained);
							}
							
							totalList = new ArrayList<>(substractLeavesMap.values());
							System.out.println("Your have more " + totalcountofleavesRemaining + " pending leaves for the year.");
//							System.out.println(totalList);
//							System.out.println(list_Maintained.get(1).getEmpname());
							
						}
						
					}
					
//-------------------------------------------------------------------------------------------------	
					
				}else {
					System.out.println("Employee ID doesn't matches with Employee Name");
					System.out.println("Enter valid credentials");
				}
			}else {
				System.out.println("Enter valid credentials");
			}
			
			return "lms";
	}
}

