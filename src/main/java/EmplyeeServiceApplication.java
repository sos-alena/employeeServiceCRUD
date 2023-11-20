import employee.service.crud.controller.AddressController;
import employee.service.crud.controller.DepartmentController;
import employee.service.crud.controller.EmployeeController;
import employee.service.crud.controller.ModelController;
import employee.service.crud.repositories.AddressRepositoryIml;
import employee.service.crud.repositories.DepartmentRepositoryIml;
import employee.service.crud.repositories.EmployeeRepositoryIml;
import employee.service.crud.service.AddressService;
import employee.service.crud.service.DepartmentService;
import employee.service.crud.service.EmployeeService;

import java.sql.SQLException;

public class EmplyeeServiceApplication {

    public static void main(String[] args) throws SQLException {

        AddressRepositoryIml addressRepositoryIml = new AddressRepositoryIml();
        DepartmentRepositoryIml departmentRepositoryIml = new DepartmentRepositoryIml();
        EmployeeRepositoryIml employeeRepositoryIml = new EmployeeRepositoryIml();

        AddressService addressService = new AddressService(addressRepositoryIml);
        DepartmentService departmentService = new DepartmentService(departmentRepositoryIml);
        EmployeeService employeeService = new EmployeeService(employeeRepositoryIml);

        AddressController addressController = new AddressController(addressService);
        DepartmentController departmentController = new DepartmentController(departmentService, employeeService);
        EmployeeController employeeController = new EmployeeController(addressController, employeeService, departmentController);

        ModelController modelController = new ModelController(addressController, departmentController, employeeController);

        modelController.app();

    }
}
