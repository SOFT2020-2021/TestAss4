package businessLayer.controllers;

import contract.Contract;

import java.io.Serializable;
import java.util.List;

public interface EntityController<T extends Serializable> {

    public Contract<T> getOne();

    public List<Contract<T>> getMany();

    public boolean deleteOne();

    public boolean deleteMany();

    public boolean persist();

    public boolean update();

}
