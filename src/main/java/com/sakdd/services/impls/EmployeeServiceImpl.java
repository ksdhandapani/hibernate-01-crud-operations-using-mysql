package com.sakdd.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakdd.dtos.ApiResponseVO;
import com.sakdd.dtos.EmployeeVO;
import com.sakdd.models.Employee;
import com.sakdd.repositories.EmployeeRepository;
import com.sakdd.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ApiResponseVO getEmployees() {
		List<EmployeeVO> employeesVO = new ArrayList<EmployeeVO>();
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		try {
			List<Employee> employees = employeeRepository.findAll();
			if (!employees.isEmpty()) {
				for (int i = 0; i < employees.size(); i++) {
					Employee employee = employees.get(i);
					EmployeeVO employeeVO = new EmployeeVO();
					employeeVO.setEmployeeId(employee.getEmployeeId());
					employeeVO.setEmployeeName(employee.getEmployeeName());
					employeeVO.setDepartment(employee.getDepartment());
					employeeVO.setGender(employee.getGender());
					employeesVO.add(employeeVO);
				}
				apiResponse.setData(employeesVO);
				apiResponse.setSuccess(true);
				apiResponse.setMessage("Employees found");
			} else {
				apiResponse.setMessage("Employees not found");
			}
		} catch (Exception ex) {
			apiResponse.setException(true);
			apiResponse.setMessage("Internal Error occured");
		}
		return apiResponse;
	}

	@Override
	public ApiResponseVO getEmployeeById(int id) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		Employee employee = new Employee();
		EmployeeVO employeeVO = new EmployeeVO();
		try {
			employee = employeeRepository.findById(id).get();
			if (employee != null) {
				employeeVO.setDepartment(employee.getDepartment());
				employeeVO.setEmployeeId(employee.getEmployeeId());
				employeeVO.setGender(employee.getGender());
				employeeVO.setEmployeeName(employee.getEmployeeName());
				apiResponse.setData(employeeVO);
				apiResponse.setSuccess(true);
				apiResponse.setMessage("Employee with Id " + id + " found");
			} else {
				apiResponse.setMessage("Employee with Id " + id + " not found");
			}

		} catch (Exception ex) {
			apiResponse.setException(true);
			apiResponse.setMessage("Internal Error occured");
		}
		return apiResponse;
	}

	@Override
	public ApiResponseVO saveEmployee(EmployeeVO employeeVO) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		EmployeeVO savedEmployeeVO = new EmployeeVO();
		try {
			Employee employee = new Employee();
			employee.setDepartment(employeeVO.getDepartment());
			employee.setGender(employeeVO.getGender());
			employee.setEmployeeId(employeeVO.getEmployeeId());
			employee.setEmployeeName(employeeVO.getEmployeeName());
			Employee emp = employeeRepository.save(employee);
			if (emp != null) {
				savedEmployeeVO.setDepartment(emp.getDepartment());
				savedEmployeeVO.setEmployeeId(emp.getEmployeeId());
				savedEmployeeVO.setGender(emp.getGender());
				savedEmployeeVO.setEmployeeName(emp.getEmployeeName());
				apiResponse.setData(savedEmployeeVO);
				apiResponse.setSuccess(true);
				apiResponse.setMessage("Employee saved successfully");
			}

		} catch (Exception ex) {
			apiResponse.setException(true);
			apiResponse.setMessage("Internal Error occured");
		}
		return apiResponse;
	}

	@Override
	public ApiResponseVO updateEmployee(EmployeeVO employeeVO) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		EmployeeVO savedEmployeeVO = new EmployeeVO();
		try {
			Employee foundEmployee = this.employeeRepository.findById(employeeVO.getEmployeeId()).get();
			System.out.println("Found Employee- "+foundEmployee.toString());
			if (foundEmployee != null) {
				System.out.println("Employee found");
				foundEmployee.setDepartment(employeeVO.getDepartment());
				foundEmployee.setEmployeeName(employeeVO.getEmployeeName());
				foundEmployee.setGender(employeeVO.getGender());
				foundEmployee.setEmployeeId(employeeVO.getEmployeeId());
				Employee savedEmployee = this.employeeRepository.save(foundEmployee);
				if (savedEmployee != null) {
					savedEmployeeVO.setDepartment(savedEmployee.getDepartment());
					savedEmployeeVO.setEmployeeId(savedEmployee.getEmployeeId());
					savedEmployeeVO.setGender(savedEmployee.getGender());
					savedEmployeeVO.setEmployeeName(savedEmployee.getEmployeeName());
					apiResponse.setData(savedEmployeeVO);
					apiResponse.setSuccess(true);
					apiResponse.setMessage("Employee updated successfully");
				} else {
					apiResponse.setData(null);
					apiResponse.setMessage("Employee with Id " + employeeVO.getEmployeeId() + " found but not updated");
					apiResponse.setSuccess(false);
				}
			}
		} catch (Exception ex) {
			apiResponse.setException(true);
			apiResponse.setMessage("Internal Error occured "+ex.getMessage());
		}
		return apiResponse;
	}

	@Override
	public ApiResponseVO deleteEmployeeById(int id) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		try {
			Employee employee = employeeRepository.findById(id).get();
			if (employee != null) {
				employeeRepository.deleteById(id);
				apiResponse.setMessage("Employee with Id " + id + " deleted successfully");
				apiResponse.setSuccess(true);
			} else {
				apiResponse.setMessage("Employee with Id " + id + " not found");
			}
		} catch (Exception ex) {
			apiResponse.setException(true);
			apiResponse.setMessage("Internal Error occured");
		}
		return apiResponse;
	}

}
