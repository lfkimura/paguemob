package br.com.paguemob.kimura.interview.rest;

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

import br.com.paguemob.kimura.interview.service.EmployeeService;
import br.com.paguemob.kimura.interview.vo.EmployeeVO;

@Path("/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees(@QueryParam("jobTitle") String jobTitle, @QueryParam("page") Integer page,
			@QueryParam("maxResults") Integer maxResults) {
		List<EmployeeVO> employees;
		if (shouldBePaginated(page, maxResults)) {
			employees = service.getEmployees(jobTitle, new PageRequest(page-1, maxResults));
		} else
			employees = service.getEmployees(jobTitle);

		return Response.ok(employees).build();

	}

	private boolean shouldBePaginated(Integer page, Integer maxResults) {
		return page != null && maxResults != null && page > 0;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response newEmployee(@Context UriInfo uriInfo, EmployeeVO employee) {
		Long employeeId = service.createEmployee(employee).getId();
		UriBuilder location = uriInfo.getAbsolutePathBuilder();
		location.path(Long.toString(employeeId));
		return Response.created(location.build()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("id") String id) {
		return Response.ok(service.getEmployee(id)).build();
	}

}
