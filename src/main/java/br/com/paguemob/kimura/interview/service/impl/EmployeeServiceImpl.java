package br.com.paguemob.kimura.interview.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paguemob.kimura.interview.model.Company;
import br.com.paguemob.kimura.interview.model.Employee;
import br.com.paguemob.kimura.interview.repository.CompanyRepository;
import br.com.paguemob.kimura.interview.repository.EmployeeRepository;
import br.com.paguemob.kimura.interview.service.EmployeeService;
import br.com.paguemob.kimura.interview.vo.EmployeeVO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeVO> getEmployees(String jobTitle) {
		if (jobTitle == null) {
			return ((List<Employee>) employeeRepository.findAll()).stream().map(employee -> new EmployeeVO(employee))
					.collect(Collectors.toList());
		} else
			return employeeRepository.findJobTitleLike(jobTitle).stream().map(employee -> new EmployeeVO(employee))
					.collect(Collectors.toList());
	}

	@Override
	public EmployeeVO createEmployee(EmployeeVO employeeVO) {
		Company company = companyRepository.findOne(employeeVO.getEmployer());
		return new EmployeeVO(employeeRepository.save(new Employee(employeeVO, company)));
	}

	@Override
	public EmployeeVO getEmployee(String id) {
		Employee employee;
		EmployeeVO employeeVO = (employee = employeeRepository.findOne(Long.valueOf(id))) != null
				? new EmployeeVO(employee) : null;
		return employeeVO;
	}

}
