package businessLayer.controllers;

import contract.Contract;
import contract.transferables.TransactionTransferable;
import dataLayer.DAO;
import dataLayer.performers.AccountPerformer;
import dataLayer.performers.TransactionPerformer;

import java.util.List;

public class TransactionController implements EntityController<TransactionTransferable> {

    public void createTransactionAndUpdateBalance (int sender, int retriever , long amount) throws Exception{
        DAO.connect("jdbc:postgresql://192.168.1.137:5432/bank");
        AccountPerformer test = new AccountPerformer();
        TransactionPerformer test1 = new TransactionPerformer();
        test1.createTransaction(sender,retriever,amount);
        DAO.close();
    }

    @Override
    public Contract<TransactionTransferable> getOne() {
        return null;
    }

    @Override
    public List<Contract<TransactionTransferable>> getMany() {
        return null;
    }

    @Override
    public boolean deleteOne() {
        return false;
    }

    @Override
    public boolean deleteMany() {
        return false;
    }

    @Override
    public boolean persist() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
