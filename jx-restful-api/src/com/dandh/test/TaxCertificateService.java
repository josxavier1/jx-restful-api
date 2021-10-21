package com.dandh.test;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@ApplicationPath(value = "/")
@Path("/TaxCertificate")
public class TaxCertificateService  extends Application {

	@POST
	@Path("/")
	@Produces({ "application/json" })
	private void addTaxCertificate() {
		
		
	}

	//adding lot of nonsense some more nonsense
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
