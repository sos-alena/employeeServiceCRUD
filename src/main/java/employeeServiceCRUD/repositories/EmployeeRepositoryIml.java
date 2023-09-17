package employeeServiceCRUD.repositories;

import employeeServiceCRUD.repositories.bl.SessionWrapper;
import employeeServiceCRUD.models.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class EmployeeRepositoryIml extends SessionWrapper implements EmployeeRepository{
    @Override
    public void add(Employee employee) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(employee);

        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public List<Employee> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM EMPLOYEE";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        List<Employee> employeeList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return employeeList;
    }

    @Override
    public Employee getById(Long id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM EMPLOYEE WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        query.setParameter("id", id);

        Employee employee = (Employee) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return employee;
    }

    @Override
    public void update(Employee employee) throws SQLException {
//open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(employee);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(Employee employee) throws SQLException {
//open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(employee);

        //close session with a transaction
        closeTransactionSession();
    }

}
