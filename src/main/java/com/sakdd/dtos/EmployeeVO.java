package com.sakdd.dtos;

public class EmployeeVO {
	private Integer employeeId;
	private String employeeName;
	private String gender;
	private String department;
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public EmployeeVO(Integer employeeId, String employeeName, String gender, String department) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.gender = gender;
		this.department = department;
	}
	public EmployeeVO() {
		super();

	}
	@Override
	public String toString() {
		return "EmployeeVO [employeeId=" + employeeId + ", employeeName=" + employeeName + ", gender=" + gender
				+ ", department=" + department + "]";
	}
	

}
