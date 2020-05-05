package ResourceLayer;

import businessLayer.controllers.TransactionController;
import contract.JSON;
import contract.transferables.TransactionActivityTransferable;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

@Path("Transactions")
public class Transactions {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTransaction (String temp) throws IOException {
        try {
            TransactionController tc = new TransactionController();
            JSON json = new JSON();
            TransactionActivityTransferable tat = json.fromJSONTransactionActivity(temp);

            tc.createTransactionAndUpdateBalance(tat.getSenderAccountId(), tat.getReceiverAccountId(), tat.getAmount());
            return Response.status(200).entity(temp).build();
        }
        catch (Exception E){
            return Response.status(500).entity(E.getMessage()).build();
        }
    }
}
