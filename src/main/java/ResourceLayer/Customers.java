package ResourceLayer;

import businessLayer.controllers.CustomerController;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("Customers")
public class Customers {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        try {
            CustomerController cc = new CustomerController();
            String customerIdsJSON = cc.getAllCustomersID();
            return Response.status(200).entity(customerIdsJSON).build();
        }
        catch(Exception E){
            return Response.status(500).entity("Something went wrong").build();
        }
    }


    @GET
    @Path("{cpr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("cpr") String cpr) {
        CustomerController cc = new CustomerController();
        return Response.status(200).entity(cc.getOne(cpr)).build();

    }
}
