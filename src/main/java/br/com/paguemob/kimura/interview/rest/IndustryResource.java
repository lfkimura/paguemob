package br.com.paguemob.kimura.interview.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.paguemob.kimura.interview.service.CompanyService;

@Path("/industry")
public class IndustryResource {

	@Autowired
	private CompanyService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIndustries() {
		return Response.ok(service.geIndustries()).build();
	}
	

}
