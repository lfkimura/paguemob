package br.com.paguemob.kimura.interview;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.com.paguemob.kimura.interview.rest.CompanyResource;
import br.com.paguemob.kimura.interview.rest.EmployeeResource;
import br.com.paguemob.kimura.interview.rest.IndustryResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(CompanyResource.class);
		register(EmployeeResource.class);
		register(IndustryResource.class);
	}

}
