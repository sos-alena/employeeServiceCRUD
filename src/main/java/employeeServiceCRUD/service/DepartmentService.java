package employeeServiceCRUD.service;

import employeeServiceCRUD.models.Department;
import employeeServiceCRUD.repositories.DepartmentRepositoryIml;

import java.sql.SQLException;
import java.util.List;

public class DepartmentService {

    DepartmentRepositoryIml departmentRepositoryIml;

    public DepartmentService(DepartmentRepositoryIml departmentRepositoryIml) {
        this.departmentRepositoryIml = departmentRepositoryIml;
    }

    public void add(Department department) throws SQLException{
        departmentRepositoryIml.add(department);
    }
    public List<Department> getAll() throws SQLException{
        return departmentRepositoryIml.getAll();
    }


    public Department getById(Long id) throws SQLException {
        return departmentRepositoryIml.getById(id);
    }


    public void update(Department department) throws SQLException {
        departmentRepositoryIml.update(department);
    }


    public void remove(Department department) throws SQLException {
        departmentRepositoryIml.remove(department);
    }

}
