package com.sakdd.services;

import com.sakdd.dtos.ApiResponseVO;
import com.sakdd.dtos.EmployeeVO;

public interface EmployeeService {
	ApiResponseVO getEmployees();

	ApiResponseVO getEmployeeById(int id);

	ApiResponseVO saveEmployee(EmployeeVO employeeVO);
	
	ApiResponseVO updateEmployee(EmployeeVO employeeVO);

	ApiResponseVO deleteEmployeeById(int id);
}
