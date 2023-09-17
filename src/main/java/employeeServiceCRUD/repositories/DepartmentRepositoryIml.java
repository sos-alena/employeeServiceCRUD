package employeeServiceCRUD.repositories;

import employeeServiceCRUD.repositories.bl.SessionWrapper;
import employeeServiceCRUD.models.Department;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class DepartmentRepositoryIml extends SessionWrapper implements DepartmentRepository{
    @Override
    public void add(Department department) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(department);

        //close session with a transaction
        closeTransactionSession();

    }

    @Override
    public List<Department> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM DEPARTMENT";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Department.class);
        List<Department> departmentList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return departmentList;
    }

    @Override
    public Department getById(Long id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM DEPARTMENT WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Department.class);
        query.setParameter("id", id);

        Department department = (Department) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return department;
    }

    @Override
    public void update(Department department) throws SQLException {
//open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(department);

        //close session with a transaction
        closeTransactionSession();
    }

    @Override
    public void remove(Department department) throws SQLException {
//open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(department);

        //close session with a transaction
        closeTransactionSession();
    }
}
