package br.com.paguemob.kimura.interview.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.filters.FilterOperatorType;
import br.com.paguemob.kimura.interview.model.Company;
import br.com.paguemob.kimura.interview.service.CompanyService;

@Path("/company")
public class CompanyResource {

	@Autowired
	private CompanyService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanies(@QueryParam("name") String name, @QueryParam("industry") String industry) {
		List<Filter<String>> filters = addFilters(name, industry);
		return Response.ok(service.getCompanies(filters)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response newCompanny(@Context UriInfo uriInfo, Company company) {
		Long companyId = service.createCompany(company).getId();
		UriBuilder location = uriInfo.getAbsolutePathBuilder();
		location.path(Long.toString(companyId));
		return Response.created(location.build()).build();
	}

	private List<Filter<String>> addFilters(String name, String industry) {
		List<Filter<String>> filters = new ArrayList<Filter<String>>();
		if (name != null)
			filters.add(new Filter("name", FilterOperatorType.LIKE, name));
		if (industry != null)
			filters.add(new Filter("industry", FilterOperatorType.EQUAL, industry));
		return filters;
	}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompany(@PathParam("id") String id) {
		return Response.ok(service.getCompany(id)).build();
	}


}
