package br.com.paguemob.kimura.interview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.paguemob.kimura.interview.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public List<Employee> findByJobTitleLike(String jobTitle);

}