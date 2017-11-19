package br.com.paguemob.kimura.interview.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.paguemob.kimura.interview.enums.IndustryType;
import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.model.Company;
import br.com.paguemob.kimura.interview.repository.CompanyRepository;
import br.com.paguemob.kimura.interview.service.CompanyService;
import br.com.paguemob.kimura.interview.vo.CompanyVO;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<CompanyVO> getCompanies(List<Filter<String>> filters) {
		return (List<CompanyVO>) companyRepository.findCompaniesWithFilters(filters).stream()
				.map(company -> new CompanyVO(company)).collect(Collectors.toList());
	}

	@Override
	public CompanyVO createCompany(CompanyVO company) {
		return new CompanyVO(companyRepository.save(new Company(company)));
	}

	@Override
	public CompanyVO getCompany(String id) {
		Company company;
		CompanyVO companyVO = (company = companyRepository.findOne(Long.valueOf(id))) != null ? new CompanyVO(company)
				: null;
		return companyVO;

	}

	@Override
	public List<IndustryType> geIndustries() {

		return new ArrayList<IndustryType>(Arrays.asList(IndustryType.values()));
	}

	@Override
	public List<CompanyVO> getCompanies(List<Filter<String>> filters, Pageable pageRequest) {
		return ((List<Company>) companyRepository.findCompaniesWithFilters(filters,  pageRequest).getContent()).stream()
				.map(company -> new CompanyVO(company)).collect(Collectors.toList());
	}


}
