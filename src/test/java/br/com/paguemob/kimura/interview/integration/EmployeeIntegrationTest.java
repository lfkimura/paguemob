package br.com.paguemob.kimura.interview.integration;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.paguemob.kimura.interview.InterviewApplication;
import br.com.paguemob.kimura.interview.model.Company;
import br.com.paguemob.kimura.interview.model.Employee;
import br.com.paguemob.kimura.interview.repository.EmployeeRepository;

/* This API
  Should be able to return a list of employees that work at a specified Company
	Should be able to register an Employee
	Should be able to return a list of all Employees
	Should be able to return a list of Employees whose Job Title contains a specified word
	Should be able to return a single Employee with a specified id
	Should be able to return a list with all supported Industries (List can have any entries, but at least 10 pre-definied items)
*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = InterviewApplication.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class EmployeeIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void shouldGetEmployees() {
		new Employee("Luis", "male", "teste@teste.com.br", "30255588852", new Company(), "analista de teste", "luis");

		ResponseEntity<Employee> entity = this.restTemplate.getForEntity("/rest/employee", Employee.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

		assertThat(entity.getBody().getName()).isEqualTo("Luis");
	}

}
