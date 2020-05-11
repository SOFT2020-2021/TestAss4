package resourceLayer;

import businessLayer.controllers.AccountController;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("accounts")
public class Accounts {

    @GET
    @Path("{cpr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByCpr(@PathParam("cpr") String cpr) {
        try {
            AccountController ac = new AccountController();
            String accountsJSON  = ac.getAccountsByCustomerID(cpr);
            return Response.status(200).entity(accountsJSON).build();
        }
        catch(Exception E){
            return Response.status(500).entity(E.getMessage()).build();
        }
    }
}
