package br.com.paguemob.kimura.interview.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.model.Company;
import br.com.paguemob.kimura.interview.repository.CompanyRepository;
import br.com.paguemob.kimura.interview.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> getCompanies(List<Filter<String>> filters) {
		return (List<Company>) companyRepository.findCompaniesWithFilters(filters);
	}

	@Override
	public Company createCompany(Company company) {
		return companyRepository.save(company);
	}

}
