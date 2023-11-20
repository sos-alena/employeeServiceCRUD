package employee.service.crud.service;

import employee.service.crud.models.Address;
import employee.service.crud.repositories.AddressRepository;
import employee.service.crud.repositories.AddressRepositoryIml;
import java.sql.SQLException;
import java.util.List;

public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepositoryIml addressRepositoryIml) {
        this.addressRepository = addressRepositoryIml;
    }

    public void add(Address address) throws SQLException {
        addressRepository.add(address);
    }

    public List<Address> getAll() throws SQLException {

       return addressRepository.getAll();

    }

    public Address getById(Long id) throws SQLException{
        return addressRepository.getById(id);
    }
    public void update(Address e) throws SQLException{
        addressRepository.update(e);
    }

    public void remove(Address e) throws SQLException{
        addressRepository.remove(e);
    }
}

