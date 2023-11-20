package employee.service.crud.repositories;

import employee.service.crud.repositories.bl.SessionWrapper;
import employee.service.crud.models.Department;
import org.hibernate.Query;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

public class DepartmentRepositoryIml extends SessionWrapper implements DepartmentRepository{
    @Override
    public void add(Department department) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.save(department);

        closeTransactionSession();

    }

    @Override
    public List<Department> getAll() throws SQLException {

        openTransactionSession();

        String sql = "SELECT * FROM DEPARTMENT";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Department.class);
        List<Department> departmentList = query.list();

        closeTransactionSession();

        return departmentList;
    }

    @Override
    public Department getById(Long id) throws SQLException {

        openTransactionSession();

        String sql = "SELECT * FROM DEPARTMENT WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Department.class);
        query.setParameter("id", id);

        Department department = (Department) query.getSingleResult();

        closeTransactionSession();

        return department;
    }

    @Override
    public void update(Department department) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.update(department);

        closeTransactionSession();
    }
    @Override
    public void remove(Department department) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.remove(department);

        closeTransactionSession();
    }
}
