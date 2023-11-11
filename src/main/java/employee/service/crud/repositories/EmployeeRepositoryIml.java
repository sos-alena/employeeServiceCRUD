package employee.service.crud.repositories;

import employee.service.crud.repositories.bl.SessionWrapper;
import employee.service.crud.models.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class EmployeeRepositoryIml extends SessionWrapper implements EmployeeRepository{
    @Override
    public void add(Employee employee) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.save(employee);

        closeTransactionSession();

    }

    @Override
    public List<Employee> getAll() throws SQLException {

        openTransactionSession();

        String sql = "SELECT * FROM EMPLOYEE";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        List<Employee> employeeList = query.list();

        closeTransactionSession();

        return employeeList;
    }

    @Override
    public Employee getById(Long id) throws SQLException {

        openTransactionSession();

        String sql = "SELECT * FROM EMPLOYEE WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        query.setParameter("id", id);

        Employee employee = (Employee) query.getSingleResult();

        closeTransactionSession();

        return employee;
    }

    @Override
    public void update(Employee employee) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.update(employee);

        closeTransactionSession();
    }

    @Override
    public void remove(Employee employee) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.remove(employee);

        closeTransactionSession();
    }
}
