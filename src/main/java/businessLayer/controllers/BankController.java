package businessLayer.controllers;

import contract.Contract;
import contract.transferables.BankTransferable;

import java.util.List;

public class BankController implements EntityController<BankTransferable> {

    @Override
    public Contract<BankTransferable> getOne() {
        return null;
    }

    @Override
    public List<Contract<BankTransferable>> getMany() {
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
