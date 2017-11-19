package br.com.paguemob.kimura.interview.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.paguemob.kimura.interview.vo.EmployeeVO;

public interface EmployeeService {
	
	public List<EmployeeVO> getEmployees(String jobTitle);

	public EmployeeVO createEmployee(EmployeeVO employeeVO);

	public EmployeeVO getEmployee(String id);

	public List<EmployeeVO> getEmployees(String jobTitle, Pageable pageRequest);

}
