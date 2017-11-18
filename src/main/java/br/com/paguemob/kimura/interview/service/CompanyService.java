package br.com.paguemob.kimura.interview.service;

import java.util.List;

import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.model.Company;

public interface CompanyService {
	
	public List<Company> getCompanies(List<Filter<String>> filters);

	public Company createCompany(Company company);

}
