package com.springbootcasestudy.LMSnew.model;

public class Model {
	
	public int empid;
	public String empname;
	public int Jan;
	public int Feb;
	public int Mar;
	public int Apr;
	public int May;
	public int Jun;
	public int Jul;
	public int Aug;
	public int Sep;
	public int Oct;
	public int Nov;
	public int Dec;
	public int Total;
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public int getJan() {
		return Jan;
	}
	public void setJan(int jan) {
		Jan = jan;
	}
	public int getFeb() {
		return Feb;
	}
	public void setFeb(int feb) {
		Feb = feb;
	}
	public int getMar() {
		return Mar;
	}
	public void setMar(int mar) {
		Mar = mar;
	}
	public int getApr() {
		return Apr;
	}
	public void setApr(int apr) {
		Apr = apr;
	}
	public int getMay() {
		return May;
	}
	public void setMay(int may) {
		May = may;
	}
	public int getJun() {
		return Jun;
	}
	public void setJun(int jun) {
		Jun = jun;
	}
	public int getJul() {
		return Jul;
	}
	public void setJul(int jul) {
		Jul = jul;
	}
	public int getAug() {
		return Aug;
	}
	public void setAug(int aug) {
		Aug = aug;
	}
	public int getSep() {
		return Sep;
	}
	public void setSep(int sep) {
		Sep = sep;
	}
	public int getOct() {
		return Oct;
	}
	public void setOct(int oct) {
		Oct = oct;
	}
	public int getNov() {
		return Nov;
	}
	public void setNov(int nov) {
		Nov = nov;
	}
	public int getDec() {
		return Dec;
	}
	public void setDec(int dec) {
		Dec = dec;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		Total = total;
	}
	
	public Model(int empid, String empname, int jan, int feb, int mar, int apr, int may, int jun, int jul, int aug,
			int sep, int oct, int nov, int dec, int total) {
		super();
		this.empid = empid;
		this.empname = empname;
		Jan = jan;
		Feb = feb;
		Mar = mar;
		Apr = apr;
		May = may;
		Jun = jun;
		Jul = jul;
		Aug = aug;
		Sep = sep;
		Oct = oct;
		Nov = nov;
		Dec = dec;
		Total = total;
	}
	public Model() {
		super();
	}
	
	@Override
	public String toString() {
		return "Model [empid=" + empid + ", empname=" + empname + ", Jan=" + Jan + ", Feb=" + Feb + ", Mar=" + Mar
				+ ", Apr=" + Apr + ", May=" + May + ", Jun=" + Jun + ", Jul=" + Jul + ", Aug=" + Aug + ", Sep=" + Sep
				+ ", Oct=" + Oct + ", Nov=" + Nov + ", Dec=" + Dec + ", Total=" + Total + "]";
	}

	
}
