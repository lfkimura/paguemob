package br.com.paguemob.kimura.interview.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.paguemob.kimura.interview.enums.IndustryType;
import br.com.paguemob.kimura.interview.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@Path("/industry")
public class IndustryResource {

	@Autowired
	private CompanyService service;

	@ApiOperation(value = "Get List of Industries", notes = "all registered industries", response = IndustryType.class, responseContainer = "List")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIndustries() {
		return Response.ok(service.geIndustries()).build();
	}

}
