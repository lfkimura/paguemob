package br.com.paguemob.kimura.interview.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.model.Company;

public interface CompanyRepositoryCustom {

	Page<Company> findCompaniesWithFilters(List<Filter<String>> filters, Pageable pageable);

	List<Company> findCompaniesWithFilters(List<Filter<String>> filters);
}
