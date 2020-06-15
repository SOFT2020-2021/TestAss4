package resourceLayer;

import businessLayer.controllers.UserController;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("users")
public class Users {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        try {
            UserController uc = new UserController();
            String userIdsJson = uc.getAllUserIds();
            return Response.status(200).entity(userIdsJson).build();
        }
        catch(Exception E){
            return Response.status(500).entity(E.getMessage()).build();
        }
    }
}
