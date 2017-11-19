package br.com.paguemob.kimura.interview.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.paguemob.kimura.interview.InterviewApplication;

/*
 * 	Should be able to return a list with all supported Industries (List can have any entries, but at least 10 pre-definied items)

 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = InterviewApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class IndustryIntegrationTest {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void shouldBeAbleToListAllSupportedIndustries() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + this.port);

		Response response = target.path("/rest/industry/").request().get();
		List<JSONObject> industries = response.readEntity(new GenericType<List<JSONObject>>() {
		});
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(industries.size()).isEqualTo(10);
	}

}
