package employee.service.crud.repositories;

import employee.service.crud.models.Address;
import employee.service.crud.repositories.bl.SessionWrapper;
import org.hibernate.Query;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

public class AddressRepositoryIml extends SessionWrapper implements AddressRepository{

    @Override
    public void add(Address e){
        openTransactionSession();

        Session session = getSession();
        session.save(e);

        closeTransactionSession();

    }

    @Override
    public List<Address> getAll() throws SQLException {

        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        List<Address> addressList = query.list();

        closeTransactionSession();
        return addressList;
    }

    @Override
    public Address getById(Long id) throws SQLException {

        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        query.setParameter("id", id);

        Address address = (Address) query.getSingleResult();

        closeTransactionSession();

        return address;
    }

    @Override
    public void update(Address e) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.update(e);

        closeTransactionSession();
    }

    @Override
    public void remove(Address e) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.remove(e);

        closeTransactionSession();
    }
}
