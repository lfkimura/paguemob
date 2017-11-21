package br.com.paguemob.kimura.interview;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.com.paguemob.kimura.interview.rest.CompanyResource;
import br.com.paguemob.kimura.interview.rest.EmployeeResource;
import br.com.paguemob.kimura.interview.rest.IndustryResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(CompanyResource.class);
		register(EmployeeResource.class);
		register(IndustryResource.class);

    	configureSwagger();
	}
	
	private void configureSwagger() {
    	register(ApiListingResource.class);
    	BeanConfig beanConfig = new BeanConfig();
    	beanConfig.setVersion("0.0.1");
    	beanConfig.setSchemes(new String[]{"http"});
    	beanConfig.setHost("localhost:8080");
    	beanConfig.setBasePath("/");
    	beanConfig.setDescription("SWaager added");
    	beanConfig.setContact("Luis Kimura");
    	beanConfig.setResourcePackage("br.com.paguemob.kimura.interview.rest");
    	beanConfig.setPrettyPrint(true);
    	beanConfig.setScan(true);
	}

}
