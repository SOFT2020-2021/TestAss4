package businessLayer.controllers;

import contract.Contract;
import contract.transferables.CustomerIdsTransferable;
import dataLayer.performers.CustomersPerformer;

import java.util.List;

public class CustomerController  {

    public String getAllCustomersID() throws Exception{
        CustomersPerformer test = new CustomersPerformer();
        List<String> alle = test.getAllCustomersID();
        Contract<CustomerIdsTransferable> temp = new CustomerIdsTransferable(alle);
        return temp.toJSON();
    }
}
