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
import br.com.paguemob.kimura.interview.model.Company;
import br.com.paguemob.kimura.interview.repository.CompanyRepository;
import br.com.paguemob.kimura.interview.vo.CompanyVO;

/*This API
   Should be able to register a Company
	 Should be able to return a list with all Companies
	 Should be able to search for Companies whose name contains a specified word
	 Should be able to return a list of Companies in a specified Industry
	 Should be able to return a single Company with a specified id
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = InterviewApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CompanyIntegrationTest {

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private CompanyRepository repository;

	@Test
	public void shouldBeAbleToListAllCompanies() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path("/rest/company/").request().get();
		List<CompanyVO> companies = response.readEntity(new GenericType<List<CompanyVO>>() {
		});
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(companies.size()).isEqualTo(5);
	}
	
	@Test
	public void shouldBeAbleToRegisterACompany() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Entity<CompanyVO> company = Entity.entity(new CompanyVO("Kimura Systems", "44.788.839/0001-01", "11996611884",
				"www.kimurasystems.com", "Software"), MediaType.APPLICATION_JSON);

		Response response = target.path("/rest/company/").request().accept(MediaType.APPLICATION_JSON).post(company);
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		String location = response.getHeaders().get("location").get(0).toString();
		assertThat(location.contains("/rest/company/"));

		Long id = Long.valueOf(location.substring(location.lastIndexOf("/") + 1));
		Company dbCompany = repository.findOne(id);
		assertThat(dbCompany.getId()).isEqualTo(id);

	}

	@Test
	public void shouldBeAbleToSearchCompaniesWithNameLike() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path("/rest/company/").queryParam("name", "Kimura").request().get();
		List<CompanyVO> companies = response.readEntity(new GenericType<List<CompanyVO>>() {
		});
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(companies.size()).isEqualTo(1);
		CompanyVO company = (CompanyVO) companies.get(0);

		assertThat(company.getName()).isEqualTo("Kimura Sistemas");
		assertThat(company.getCnpj()).isEqualTo("51.855.786/0001-60");
		assertThat(company.getWebsite()).isEqualTo("www.kimurasistemas.com");
		assertThat(company.getIndustry()).isEqualTo("Bussiness Services");
	}

	@Test
	public void shouldBeAbleToSearchCompaniesOfAnIndustry() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path("/rest/company/").queryParam("industry", "Software").request().get();
		List<CompanyVO> companies = (List<CompanyVO>) response.readEntity(new GenericType<List<CompanyVO>>() {
		});
		;
		CompanyVO company = companies.get(0);

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(companies.size()).isEqualTo(1);
		assertThat(company.getName()).isEqualTo("Google");
		assertThat(company.getCnpj()).isEqualTo("88.946.074/0001-66");
		assertThat(company.getWebsite()).isEqualTo("www.google.com");
		assertThat(company.getIndustry()).isEqualTo("Software");
	}

	@Test
	public void shouldBeAbleToReturnACompanyById() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path("/rest/company/1").request().get();
		CompanyVO company = response.readEntity(CompanyVO.class);
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(company.getName()).isEqualTo("Kimura Sistemas");
		assertThat(company.getCnpj()).isEqualTo("51.855.786/0001-60");
		assertThat(company.getWebsite()).isEqualTo("www.kimurasistemas.com");
		assertThat(company.getIndustry()).isEqualTo("Bussiness Services");

	}


	@Test
	public void shouldBeAblePaginateSearchCompaniesWithNameLike() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path("/rest/company/").queryParam("name", "Kimura").queryParam("page", 1).queryParam("maxResults", 2).request().get();
		List<CompanyVO> companies = response.readEntity(new GenericType<List<CompanyVO>>() {
		});
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(companies.size()).isEqualTo(1);
	}
}
