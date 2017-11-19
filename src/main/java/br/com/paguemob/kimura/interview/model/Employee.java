package br.com.paguemob.kimura.interview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.paguemob.kimura.interview.vo.EmployeeVO;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String nameTitle;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String gender;

	@Column
	private String email;

	@Column
	private String cpf;

	@ManyToOne
	@JoinColumn(name = "employer")
	private Company employer;

	@Column
	private String jobTitle;

	@Column
	private String seed;
	public Employee(){}

	public Employee(EmployeeVO employeeVO, Company company) {
		super();
		this.nameTitle = employeeVO.getName().getTitle();
		this.firstName = employeeVO.getName().getFirst();
		this.lastName = employeeVO.getName().getLast();
		this.gender = employeeVO.getGender();
		this.email = employeeVO.getEmail();
		this.cpf = employeeVO.getCpf();
		this.employer = company;
		this.jobTitle = employeeVO.getJobTitle();
		this.seed = employeeVO.getSeed();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
