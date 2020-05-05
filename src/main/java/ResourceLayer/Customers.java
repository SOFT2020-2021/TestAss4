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
            CustomerController temp = new CustomerController();
            String temp2 = temp.getAllCustomersID();
            return Response.status(200).entity(temp2).build();
        }
        catch(Exception E){
            return Response.status(500).entity("Something went wrong").build();
        }
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") String id) {
        CustomerController customertemp = new CustomerController();
        //customertemp.getOne();
        return Response.status(200).entity("getUserById is called, id : " + id).build();

    }
}
