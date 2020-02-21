package com.dandh.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationPath(value = "/")
@Path("/SalesCustomers")
public class SalesCustomerService extends Application {

    @Context
    UriInfo uriInfo;
	
	private static HashMap<String, SalesCustomer> salesCustomerList;

	public SalesCustomerService() {

		if (salesCustomerList == null) {
			salesCustomerList = new HashMap<String, SalesCustomer>();
		}
	}

	@POST
	@Path("/")
	@Produces({ "application/json" })
	public Response addSalesCustomer(SalesCustomer salesCustomer) throws Exception {
		System.out.println("reached addSalesCustomer!!");
		if (salesCustomerList.containsKey(salesCustomer.getCustomerId())) {
			throw new Exception("Duplicate customer id");
		}
		salesCustomerList.put(salesCustomer.getCustomerId(), salesCustomer);
		Link lnk = Link.fromUri(uriInfo.getPath() + "/" + salesCustomer.getCustomerId()).rel("self").build();
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).location(lnk.getUri()).build();
		
	}

	@GET
	@Path("/")
	@Produces({ "application/json" })
	public List<SalesCustomer> getAllSalesCustomers() {
		System.out.println("reached getAllSalesCustomers!!");
		return new ArrayList<SalesCustomer>(salesCustomerList.values());
		//return Response.status(javax.ws.rs.core.Response.Status.OK).entity(config).build();
	}

	@GET
	@Path("{customerId}")
	@Produces({ "application/json" })
	public SalesCustomer getSalesCustomer(@PathParam("customerId") String customerId) throws Exception {
		System.out.println("reached getSalesCustomer!! :  " + customerId);
		SalesCustomer salesCustomer = null;
		salesCustomer = salesCustomerList.get(customerId);
		if (salesCustomer == null) {
			throw new Exception("402");
		}
		return salesCustomer;
	}

	@POST
	@Path("/start")
	@Produces({ "application/json" })
	public Response start() throws Exception {
		System.out.println("reached start!! :  ");
		return Response.status(javax.ws.rs.core.Response.Status.OK).build();
	}

	//update response
	//return Response.status(javax.ws.rs.core.Response.Status.OK).entity(new Message("Config Updated Successfully")).build();
	
	//delete response
	//return Response.status(javax.ws.rs.core.Response.Status.OK).build();
}
