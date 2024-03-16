package com.springbootcasestudy.LMSnew.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootcasestudy.LMSnew.model.Model;
import com.springbootcasestudy.LMSnew.DButil.*;
import com.springbootcasestudy.LMSnew.service.*;


@Service
public class EmployeeServiceImpl implements LMSservice{
	
	@Autowired
	static List<Model> emplist = new ArrayList<>();
	
	Connection connection;
	
	public EmployeeServiceImpl () throws SQLException {
		connection =  DButil.getConnection();
	}
	
	@Override
	public List<Model> getEmployeeData() {
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from data");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Model obj = new Model();
				obj.setEmpid(rs.getInt(1));
				obj.setEmpname(rs.getString(2));
				obj.setJan(rs.getInt(3));
				obj.setFeb(rs.getInt(4));
				obj.setMar(rs.getInt(5));
				obj.setApr(rs.getInt(6));
				obj.setMay(rs.getInt(7));
				obj.setJun(rs.getInt(8));
				obj.setJul(rs.getInt(9));
				obj.setAug(rs.getInt(10));
				obj.setSep(rs.getInt(11));
				obj.setOct(rs.getInt(12));
				obj.setNov(rs.getInt(13));
				obj.setDec(rs.getInt(14));
				obj.setTotal(rs.getInt(15));
				
				emplist.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emplist;
	}
	
	public void updateEmployeeData(List<Model> emplist) {
	    try {
	        PreparedStatement ps = connection.prepareStatement("UPDATE data SET `Jan`=?, `Feb`=?, `Mar`=?, `Apr`=?, `May`=?, `Jun`=?, `Jul`=?, `Aug`=?, `Sep`=?, `Oct`=?, `Nov`=?, `Dec`=?, `Total`=? WHERE `Employee_ID`=?");

	        for (Model obj : emplist) {
	            ps.setInt(1, obj.getJan());
	            ps.setInt(2, obj.getFeb());
	            ps.setInt(3, obj.getMar());
	            ps.setInt(4, obj.getApr());
	            ps.setInt(5, obj.getMay());
	            ps.setInt(6, obj.getJun());
	            ps.setInt(7, obj.getJul());
	            ps.setInt(8, obj.getAug());
	            ps.setInt(9, obj.getSep());
	            ps.setInt(10, obj.getOct());
	            ps.setInt(11, obj.getNov());
	            ps.setInt(12, obj.getDec());
	            ps.setInt(13, obj.getTotal());
	            ps.setInt(14, obj.getEmpid());

	            ps.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
