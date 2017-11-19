package br.com.paguemob.kimura.interview.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.paguemob.kimura.interview.InterviewApplication;
import br.com.paguemob.kimura.interview.model.Employee;
import br.com.paguemob.kimura.interview.repository.EmployeeRepository;
import br.com.paguemob.kimura.interview.vo.EmployeeVO;
import br.com.paguemob.kimura.interview.vo.NameVO;

/* This API
  Should be able to return a list of employees that work at a specified Company
	Should be able to register an Employee -
	Should be able to return a list of all Employees -
	Should be able to return a list of Employees whose Job Title contains a specified word
	Should be able to return a single Employee with a specified id
*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = InterviewApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class EmployeeIntegrationTest {

	private static final String REST_EMPLOYEE = "/rest/employee/";

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void shouldBeAbleToRegisterAnEmployee() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		NameVO name = new NameVO("", "Gabriela", "Kimura");

		Entity<EmployeeVO> employee = Entity.entity(new EmployeeVO(name, "female", "Gabriela@zipmail.com",
				"302.166.654-55", (long) 2, "Finance Executive ", ""), MediaType.APPLICATION_JSON);

		Response response = target.path(REST_EMPLOYEE).request().accept(MediaType.APPLICATION_JSON).post(employee);
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		String location = response.getHeaders().get("location").get(0).toString();
		assertThat(location.contains(REST_EMPLOYEE));

		Long id = Long.valueOf(location.substring(location.lastIndexOf("/") + 1));
		Employee dbCompany = repository.findOne(id);
		assertThat(dbCompany.getId()).isEqualTo(id);

	}

	@Test
	public void shouldListAllEmployees() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path(REST_EMPLOYEE).request().get();
		List<EmployeeVO> employees = response.readEntity(new GenericType<List<EmployeeVO>>() {
		});
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(employees.size()).isEqualTo(5);
	}

	@Test
	public void shouldBeAbleToSearchAnEmployeeByJobTitle() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path(REST_EMPLOYEE).queryParam("jobTitle", "Dev").request().get();
		List<EmployeeVO> employees = response.readEntity(new GenericType<List<EmployeeVO>>() {
		});
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(employees.size()).isEqualTo(1);
		EmployeeVO employee = (EmployeeVO) employees.get(0);

		assertThat(employee.getName().getFirst()).isEqualTo("Luis Fernando");
		assertThat(employee.getCpf()).isEqualTo("222.255.568.-47");
		assertThat(employee.getJobTitle()).contains("Developer");
	}

	@Test
	public void shouldBeAbleToReturnAEmployeeById() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path("/rest/employee/1").request().get();
		EmployeeVO employee = response.readEntity(EmployeeVO.class);
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(employee.getName().getFirst()).isEqualTo("Luis Fernando");
		assertThat(employee.getCpf()).isEqualTo("222.255.568.-47");
		assertThat(employee.getGender()).isEqualTo("male");
		assertThat(employee.getEmployer()).isEqualTo(1l);
		assertThat(employee.getJobTitle()).isEqualTo("Fuckin Developer");
	}
	

	@Test
	public void shouldPaginateListAllEmployees() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path(REST_EMPLOYEE).queryParam("page", 3).queryParam("maxResults", 2).request().get();
		List<EmployeeVO> employees = response.readEntity(new GenericType<List<EmployeeVO>>() {
		});
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(employees.size()).isEqualTo(1);
	}

}
