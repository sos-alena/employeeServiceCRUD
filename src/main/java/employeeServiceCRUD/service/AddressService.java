package employeeServiceCRUD.service;

import employeeServiceCRUD.models.Address;
import employeeServiceCRUD.repositories.AddressRepositoryIml;

import java.sql.SQLException;
import java.util.List;

public class AddressService {

    AddressRepositoryIml addressRepositoryIml;

    public AddressService(AddressRepositoryIml addressRepositoryIml) {
        this.addressRepositoryIml = addressRepositoryIml;
    }

    public void add(Address address) throws SQLException {
        addressRepositoryIml.add(address);
    }

    public List<Address> getAll() throws SQLException {

       return addressRepositoryIml.getAll();

    }

    public Address getById(Long id) throws SQLException{
        return addressRepositoryIml.getById(id);
    }
    public void update(Address e) throws SQLException{
        addressRepositoryIml.update(e);
    }

    public void remove(Address e) throws SQLException{
        addressRepositoryIml.remove(e);
    }
}

