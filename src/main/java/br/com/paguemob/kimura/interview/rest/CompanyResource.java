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
import org.springframework.data.domain.PageRequest;

import br.com.paguemob.kimura.interview.enums.IndustryType;
import br.com.paguemob.kimura.interview.filters.Filter;
import br.com.paguemob.kimura.interview.filters.FilterOperatorType;
import br.com.paguemob.kimura.interview.service.CompanyService;
import br.com.paguemob.kimura.interview.vo.CompanyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api(value = "/company")
@Path("/company")
public class CompanyResource {

	@Autowired
	private CompanyService service;

	@GET
	@ApiOperation(value = "Returns companies by industry", notes = "Returns a list of all industries", responseContainer = "list", authorizations = @Authorization(value = "api_key"))
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanies(@QueryParam("name") String name, @QueryParam("industry") String industry,
			@QueryParam("page") Integer page, @QueryParam("maxResults") Integer maxResults) {
		List<Filter<String>> filters = addFilters(name, industry);
		List<CompanyVO> companies;
		if (shouldBePaginated(page, maxResults)) {
			companies = service.getCompanies(filters, new PageRequest(page - 1, maxResults));
		} else
			companies = service.getCompanies(filters);

		return Response.ok(companies).build();
	}

	private boolean shouldBePaginated(Integer page, Integer maxResults) {
		return page != null && maxResults != null && page > 0;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response newCompanny(@Context UriInfo uriInfo, CompanyVO company) {
		Long companyId = service.createCompany(company).getId();
		UriBuilder location = uriInfo.getAbsolutePathBuilder();
		location.path(Long.toString(companyId));
		return Response.created(location.build()).build();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Filter<String>> addFilters(String name, String industry) {
		List<Filter<String>> filters = new ArrayList<Filter<String>>();
		if (name != null)
			filters.add(new Filter("name", FilterOperatorType.LIKE, name));
		if (industry != null)
			filters.add(new Filter("industry", FilterOperatorType.EQUAL, IndustryType.findByName(industry)));
		return filters;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompany(@PathParam("id") String id) {
		return Response.ok(service.getCompany(id)).build();
	}

}
