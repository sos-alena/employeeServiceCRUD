import employeeServiceCRUD.controller.AddressController;
import employeeServiceCRUD.controller.DepartmentController;
import employeeServiceCRUD.controller.EmployeeController;
import employeeServiceCRUD.controller.ModelController;
import employeeServiceCRUD.repositories.AddressRepositoryIml;
import employeeServiceCRUD.repositories.DepartmentRepositoryIml;
import employeeServiceCRUD.repositories.EmployeeRepositoryIml;
import employeeServiceCRUD.service.AddressService;
import employeeServiceCRUD.service.DepartmentService;
import employeeServiceCRUD.service.EmployeeService;

import java.sql.SQLException;

public class Start {

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
