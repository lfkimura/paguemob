package br.com.paguemob.kimura.interview.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.paguemob.kimura.interview.enums.IndustryType;
import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.vo.CompanyVO;

public interface CompanyService {

	public List<CompanyVO> getCompanies(List<Filter<String>> filters);

	public CompanyVO createCompany(CompanyVO company);

	public CompanyVO getCompany(String id);

	public List<IndustryType> geIndustries();

	public List<CompanyVO> getCompanies(List<Filter<String>> filters, Pageable pageRequest);

}
