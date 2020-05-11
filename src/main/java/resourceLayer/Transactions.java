package resourceLayer;

import businessLayer.controllers.TransactionController;
import contract.JSON;
import contract.transferables.TransactionActivityTransferable;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

@Path("transactions")
public class Transactions {

    @GET
    @Path("{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByCpr(@PathParam("accountId") String accountId) {
        try {
            TransactionController tc = new TransactionController();
            String userTransactionsJson = tc.getTransactionByAccountId(Integer.parseInt(accountId));
            return Response.status(200).entity(userTransactionsJson).build();
        } catch (Exception E) {
            return Response.status(500).entity(E.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createTransaction (String transactionJson) {
        try {
            TransactionController tc = new TransactionController();
            JSON json = new JSON();
            TransactionActivityTransferable tat = json.fromJSONTransactionActivity(transactionJson);
            tc.createTransactionAndUpdateBalance(tat.getSenderAccountId(), tat.getReceiverAccountId(), tat.getAmount());
            return Response.status(200).entity(transactionJson).build();
        }
        catch (Exception E){
            return Response.status(500).entity(E.getMessage()).build();
        }
    }
}