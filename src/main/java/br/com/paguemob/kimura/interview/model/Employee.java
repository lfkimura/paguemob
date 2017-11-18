package br.com.paguemob.kimura.interview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	@Column
	private String name;

	@Column
	private String gender;

	@Column
	private String email;

	@Column
	private String cpf;
	
	@ManyToOne
	private Company employer;

	@Column
	private String jobTitle;

	@Column
	private String seed;
	
	public Employee(){}

	public Employee(String name, String gender, String email, String cpf, Company employer, String jobTitle,
			String seed) {
		super();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.cpf = cpf;
		this.employer = employer;
		this.jobTitle = jobTitle;
		this.seed = seed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Company getEmployer() {
		return employer;
	}

	public void setEmployer(Company employer) {
		this.employer = employer;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

}