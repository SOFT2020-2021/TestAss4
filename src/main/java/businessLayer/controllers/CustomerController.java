package businessLayer.controllers;

import contract.Contract;
import contract.transferables.CustomerIdsTransferable;
import contract.transferables.CustomerTransferable;
import dataLayer.DAO;
import dataLayer.performers.CustomersPerformer;

import java.util.List;

public class CustomerController  {


    public String getOne(String cpr) {
        /*
        DAO.connect("jdbc:postgresql://192.168.1.137:5432/bank");
        CustomersPerformer test = new CustomersPerformer();
        List<String> alle = test.get()
        Contract<CustomerIdsTransferable> temp = new CustomerIdsTransferable(alle);

        DAO.close();

        return temp.toJSON();*/
        return null;
    }





    public String getAllCustomersID () throws Exception{
        DAO.connect("jdbc:postgresql://192.168.1.137:5432/bank");
        CustomersPerformer test = new CustomersPerformer();
        List<String> alle = test.getAllCustomersID();
        Contract<CustomerIdsTransferable> temp = new CustomerIdsTransferable(alle);

        DAO.close();

        return temp.toJSON();

    }
}
