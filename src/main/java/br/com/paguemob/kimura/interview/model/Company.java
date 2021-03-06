package br.com.paguemob.kimura.interview.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.paguemob.kimura.interview.converter.IndustryConverter;
import br.com.paguemob.kimura.interview.enums.IndustryType;
import br.com.paguemob.kimura.interview.vo.CompanyVO;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column
	private String cnpj;

	@Column
	private String telephone;

	@Column
	private String website;

	@Column
	@Convert(converter = IndustryConverter.class)
	private IndustryType industry;

	public Company() {
	}

	public Company(String name, String cnpj, String telephone, String website, IndustryType industry) {
		super();
		this.name = name;
		this.cnpj = cnpj;
		this.telephone = telephone;
		this.website = website;
		this.industry = industry;
	}

	public Company(CompanyVO company) {

		this.name = company.getName();
		this.cnpj = company.getCnpj();
		this.telephone = company.getTelephone();
		this.website = company.getWebsite();
		this.industry = IndustryType.findByName(company.getIndustry());
	}

	public Long getId() {
		return this.id;
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

	public IndustryType getIndustry() {
		return industry;
	}

	public void setIndustry(IndustryType industry) {
		this.industry = industry;
	}

}
