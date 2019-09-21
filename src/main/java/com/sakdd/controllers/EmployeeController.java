package com.sakdd.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakdd.dtos.ApiResponseVO;
import com.sakdd.dtos.EmployeeVO;
import com.sakdd.services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);	
	@Autowired
	private EmployeeService employeeService;

	
	@RequestMapping(value="/employee",method=RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<ApiResponseVO> saveEmployee( @RequestBody EmployeeVO employeeVO) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		try {
			apiResponse = employeeService.saveEmployee(employeeVO);
		} catch (Exception ex) {
			apiResponse.setData(null);
			apiResponse.setSuccess(false);
			apiResponse.setException(true);
			apiResponse.setMessage(ex.getMessage());
			return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.OK);
	}

	
	@RequestMapping(value="/employee",method=RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<ApiResponseVO> getEmployees() {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		try {
			apiResponse = employeeService.getEmployees();
		} catch (Exception ex) {
			apiResponse.setData(null);
			apiResponse.setSuccess(false);
			apiResponse.setException(true);
			apiResponse.setMessage(ex.getMessage());
			return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value="/employee/{id}",method=RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<ApiResponseVO> getEmployeeById(@PathVariable int id) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		try {
			apiResponse = employeeService.getEmployeeById(id);		
		} catch (Exception ex) {
			apiResponse.setData(null);
			apiResponse.setSuccess(false);
			apiResponse.setException(true);
			apiResponse.setMessage(ex.getMessage());
			return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.OK);
	}

	
	
	@RequestMapping(value="/employee", method=RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<ApiResponseVO> updateEmployee(@RequestBody EmployeeVO employeeVO) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		try {
			apiResponse = employeeService.updateEmployee(employeeVO);
		} catch (Exception ex) {
			apiResponse.setData(null);
			apiResponse.setSuccess(false);
			apiResponse.setException(true);
			apiResponse.setMessage(ex.getMessage());
			return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.OK);
	}

	
	@RequestMapping(value="/employee/{id}", method=RequestMethod.DELETE, produces = {"application/json"})
	public ResponseEntity<ApiResponseVO> deleteEmployeeById(@PathVariable int id) {
		ApiResponseVO apiResponse = new ApiResponseVO();
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setException(false);
		try {
			apiResponse = employeeService.deleteEmployeeById(id);
		} catch (Exception ex) {
			apiResponse.setData(null);
			apiResponse.setSuccess(false);
			apiResponse.setException(true);
			apiResponse.setMessage(ex.getMessage());
			return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApiResponseVO>(apiResponse, HttpStatus.OK);
	}
}