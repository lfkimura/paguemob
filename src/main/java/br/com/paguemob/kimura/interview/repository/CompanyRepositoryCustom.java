package br.com.paguemob.kimura.interview.repository;

import java.util.List;

import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.model.Company;

public interface CompanyRepositoryCustom {


	List<Company> findCompaniesWithFilters(List<Filter<String>> filters);

}
