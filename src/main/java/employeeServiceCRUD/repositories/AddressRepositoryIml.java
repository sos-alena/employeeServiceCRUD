package employeeServiceCRUD.repositories;

import employeeServiceCRUD.models.Address;
import employeeServiceCRUD.repositories.bl.SessionWrapper;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class AddressRepositoryIml extends SessionWrapper implements AddressRepository{

    @Override
    public void add(Address e){
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(e);

        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public List<Address> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        List<Address> addressList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return addressList;
    }

    @Override
    public Address getById(Long id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        query.setParameter("id", id);

        Address address = (Address) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return address;
    }

    @Override
    public void update(Address e) throws SQLException {
//open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(e);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(Address e) throws SQLException {
//open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(e);

        //close session with a transaction
        closeTransactionSession();
    }

}
