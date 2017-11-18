package br.com.paguemob.kimura.interview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.paguemob.kimura.interview.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findByName(String name);
}