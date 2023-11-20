package employee.service.crud.repositories;

import employee.service.crud.models.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressRepository {
    void add(Address address) throws SQLException;

    List<Address> getAll() throws SQLException;

    Address getById(Long id) throws SQLException;

    void update(Address e) throws SQLException;
    void remove(Address address) throws SQLException;
}
