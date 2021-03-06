package br.com.paguemob.kimura.interview.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.paguemob.kimura.interview.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Query("Select e from Employee e where e.jobTitle like %:jobTitle%")
	public List<Employee> findByJobTitleLike(@Param("jobTitle") String jobTitle);

	@Query("Select e from Employee e where e.jobTitle like %:jobTitle%")
	public Page<Employee> findByJobTitleLike(@Param("jobTitle") String jobTitle, Pageable page);

	public Page<Employee> findAll(Pageable page);

}