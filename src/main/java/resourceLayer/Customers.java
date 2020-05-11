package resourceLayer;

import businessLayer.controllers.CustomerController;
import dataLayer.DAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("customers")
public class Customers {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        try {
            CustomerController cc = new CustomerController();
            String customerIdsJSON = cc.getAllCustomersID();
            return Response.status(200).entity(customerIdsJSON).build();
        }
        catch(Exception E){
            return Response.status(500).entity(E.getMessage()).build();
        }
    }
}
