package businessLayer.controllers;

import contract.Contract;
import contract.transferables.UserTransaction;
import contract.transferables.UserTransactionsTransferable;
import dataLayer.performers.TransactionPerformer;

import java.util.List;

public class TransactionController  {

    public TransactionController(){ }

    TransactionPerformer tp = new TransactionPerformer();

    public void createTransactionAndUpdateBalance (long sender, long retriever , long amount) throws Exception{
        tp.createTransaction(sender,retriever,amount);
    }

    public String getTransactionByAccountId(int accountId) throws Exception {
        List<UserTransaction> userTransactions = tp.getTransactionByAccountId(accountId);
        Contract<UserTransactionsTransferable> uttContract = new UserTransactionsTransferable(userTransactions);
        return uttContract.toJSON();
    }
}
