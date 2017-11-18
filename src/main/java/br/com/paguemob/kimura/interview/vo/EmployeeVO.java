package br.com.paguemob.kimura.interview.vo;

import java.io.Serializable;

import br.com.paguemob.kimura.interview.model.Employee;

public class EmployeeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private NameVO name;

	private String gender;

	private String email;

	private String cpf;

	private Long employer;

	private String jobTitle;

	private String seed;

	public EmployeeVO(NameVO name, String gender, String email, String cpf, Long employer, String jobTitle,
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

	public EmployeeVO() {
	}

	public EmployeeVO(Employee employee) {
		super();
		this.name = new NameVO(employee.getNameTitle(), employee.getFirstName(), employee.getLastName());
		this.gender = employee.getGender();
		this.email = employee.getEmail();
		this.cpf = employee.getCpf();
		this.employer = employee.getEmployer().getId();
		this.jobTitle = employee.getJobTitle();
		this.seed = employee.getSeed();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NameVO getName() {
		return name;
	}

	public void setName(NameVO name) {
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

	public Long getEmployer() {
		return employer;
	}

	public void setEmployer(Long employer) {
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
