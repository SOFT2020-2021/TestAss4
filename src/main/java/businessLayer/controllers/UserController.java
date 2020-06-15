package businessLayer.controllers;

import contract.Contract;
import contract.transferables.UserIdsTransferable;
import dataLayer.DAO;
import dataLayer.performers.TransactionPerformer;
import dataLayer.performers.UserPerformer;
import java.util.List;

public class UserController {

    public static void main(String[] args) throws Exception {
        DAO.connect("jdbc:postgresql://127.0.0.1:5432/bank");
        Contract<UserIdsTransferable> temp = new UserIdsTransferable(new UserPerformer().getAllUserIds());
        System.out.println(temp.toJSON());
    }

    public String getAllUserIds() throws Exception{
        UserPerformer test = new UserPerformer();
        List<String> users = test.getAllUserIds();
        for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i));
        }
        System.out.println(new UserIdsTransferable(users));
        Contract<UserIdsTransferable> temp = new UserIdsTransferable(users);
        return temp.toJSON();
    }
}
