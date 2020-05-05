import contract.Contract;
import contract.transferables.CustomerIdsTransferable;
import contract.transferables.CustomerWithAccountsTransferable;
import contract.transferables.SimpleAccountTransferable;
import dataLayer.DAO;
import dataLayer.performers.AccountPerformer;
import dataLayer.performers.CustomersPerformer;
import dataLayer.performers.TransactionPerformer;

import java.util.List;

public class main {
    public static void main(String[] args) throws Exception{

            DAO.connect("jdbc:postgresql://192.168.1.137:5432/bank");
            AccountPerformer test = new AccountPerformer();
            //List<SimpleAccountTransferable> alle = test.getAllAcountsForCustomer("1107772222");
            //Contract<CustomerWithAccountsTransferable> temp = new CustomerWithAccountsTransferable("1107772222", alle);
        TransactionPerformer test1 = new TransactionPerformer();
        test1.createTransaction(1,2,500);
        //System.out.println(temp.toJSON());
          DAO.close();



    }
}
