package employeeServiceCRUD.controller;

import employeeServiceCRUD.controller.enums.Actions;
import employeeServiceCRUD.controller.enums.ModelsList;
import employeeServiceCRUD.controller.validator.InputValue;

import java.sql.SQLException;

import static employeeServiceCRUD.repositories.bl.HibernateUtil.shutdown;

public class ModelController {
    AddressController addressController;
    DepartmentController departmentController;
    EmployeeController employeeController;

    public ModelController(AddressController addressController, DepartmentController departmentController, EmployeeController employeeController) {
        this.addressController = addressController;
        this.departmentController = departmentController;
        this.employeeController = employeeController;
    }

    public void app() throws SQLException {
         String n = "N";
         System.out.println("To continue working with the APP, enter Y or N");
         String str = InputValue.inputYesOrNot();
         if (!n.equals(str)) {
             selectEntity();
             app();
         } else {
             System.out.println("Element unchanged!");
         }
         shutdown();
     }


   private void selectEntity() throws SQLException {
        ModelsList name = InputValue.inputEntity();
        switch (name) {
            case ADDRESS -> cycleChoiceActionAddress();
            case EMPLOYEE -> cycleChoiceActionEmployee();
            case DEPARTMENT -> cycleChoiceActionDepartment();
           }
    }

    private void selectActionAddress() throws SQLException {
        Actions name = InputValue.inputAction();
        switch (name) {
            case PRINT -> addressController.selectAllAddress();
            case REMOVE -> addressController.deleteAddress();
            case INSERT -> addressController.inputAddress();
            case UPDATE -> addressController.editeAddress();
        }
    }

    private void cycleChoiceActionAddress() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Address, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectActionAddress();
            cycleChoiceActionAddress();
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private void selectActionDepartment() throws SQLException {
        Actions name = InputValue.inputAction();
        switch (name) {
            case PRINT -> departmentController.selectAllDepartment();
            case REMOVE -> departmentController.deleteDepartment();
            case INSERT -> departmentController.inputDepartment();
            case UPDATE -> departmentController.editeDepartment();
        }
    }

    private void cycleChoiceActionDepartment() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Department, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectActionDepartment();
            cycleChoiceActionDepartment();
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private void selectActionEmployee() throws SQLException {
        Actions name = InputValue.inputAction();
        switch (name) {
            case PRINT -> employeeController.selectAllEmployee();
            case REMOVE -> employeeController.deleteEmployee();
            case INSERT -> employeeController.inputEmployee();
            case UPDATE -> employeeController.editeEmployee();
        }
    }

    private void cycleChoiceActionEmployee() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Employee, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectActionEmployee();
            cycleChoiceActionEmployee();
        } else {
            System.out.println("Element unchanged!");
        }
    }

}
