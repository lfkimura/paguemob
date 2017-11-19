package br.com.paguemob.kimura.interview.vo;

import br.com.paguemob.kimura.interview.model.Company;

public class CompanyVO {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String cnpj;

	private String telephone;

	private String website;

	private String industry;

	public CompanyVO(String name, String cnpj, String telephone, String website, String industry) {
		super();
		this.name = name;
		this.cnpj = cnpj;
		this.telephone = telephone;
		this.website = website;
		this.industry = industry;
	}

	public CompanyVO() {
	}

	public CompanyVO(Company company) {
		super();
		this.id = company.getId();
		this.name = company.getName();
		this.cnpj = company.getCnpj();
		this.telephone = company.getTelephone();
		this.website = company.getWebsite();
		this.industry = company.getIndustry().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

}
