package businessLayer.controllers;

import contract.Contract;
import contract.transferables.UserIdsTransferable;
import dataLayer.performers.UserPerformer;

import java.util.List;

public class UserController {

    public String getAllUserIds() throws Exception{
        UserPerformer test = new UserPerformer();
        List<String> users = test.getAllUserIds();
        Contract<UserIdsTransferable> temp = new UserIdsTransferable(users);
        return temp.toJSON();
    }
}
