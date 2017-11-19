package br.com.paguemob.kimura.interview.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.paguemob.kimura.interview.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>, CompanyRepositoryCustom {

	public Page<Company> findAll(Pageable page);

	List<Company> findByName(String name);

	Page<Company> findByName(String name, Pageable page);
}
