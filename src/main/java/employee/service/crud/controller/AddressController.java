package employee.service.crud.controller;

import employee.service.crud.controller.validator.CountryValidator;
import employee.service.crud.controller.validator.InputValue;
import employee.service.crud.models.Address;
import employee.service.crud.models.Employee;
import employee.service.crud.controller.enums.AddressColumns;
import employee.service.crud.service.AddressService;
import employee.service.crud.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

public class AddressController {

    AddressService addressService;
    EmployeeService employeeService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    public Address addAddress() throws SQLException {
        Address address = new Address();
        System.out.println("Enter country");
        String country = CountryValidator.getNameCountry();
        address.setCountry(country);
        System.out.println("Enter town");
        String town = InputValue.inputValidateStr();
        address.setTown(town);
        System.out.println("street");
        String street = InputValue.inputValidateStr();
        address.setStreet(street);
        System.out.println("postCode");
        String postCode = InputValue.inputPostCodeValue();
        address.setPostCode(postCode);

        addressService.add(address);

        return address;
    }

    public void deleteAddress() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = InputValue.inputValidateLong();
        Address address = getById(id);

        if (address == null) {
            System.out.println("Address with this ID does not exist.");
        } else {
            System.out.println(address);
            int counter = checkIdAddressInEmployee(id);
            if (counter == 0) {
                addressService.remove(address);
                System.out.println("Address successfully delete!");

            } else {
                System.out.println("Unable to delete address with id because " +
                        "it is associated with the table Employee) ");
            }
        }
    }

    public void editeAddress() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = InputValue.inputValidateLong();
        Address address = getById(id);
        if (address == null) {
            System.out.println("Address with this ID does not exist.");

        } else {
            System.out.println(address);
            handleChoiceNameTable(address);
            addressService.update(address);
            System.out.println("Address successfully update!");

        }

    }

    private void selectNameTable(Address address) {
        AddressColumns name = InputValue.inputAddressItem();
        switch (name) {
            case COUNTRY -> address.setCountry(CountryValidator.getNameCountry());
            case TOWN -> address.setTown(InputValue.inputValidateStr());
            case STREET -> address.setStreet(InputValue.inputValidateStr());
            case POST_CODE -> address.setPostCode(InputValue.inputPostCodeValue());
        }
    }

    private void handleChoiceNameTable(Address address) {
        String n = "N";
        System.out.println("To continue working with the Address, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectNameTable(address);
            handleChoiceNameTable(address);
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private int checkIdAddressInEmployee(Long id) {
        List<Employee> employees = null;
        try {
            employees = employeeService.getAll();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

        int temp = 0;
        for (Employee item : employees) {
            Address address = item.getAddress();
            if (!(address == null)) {
                Long ida = address.getId();
                if (id.equals(ida)) {

                    temp++;
                }
            }
        }
        return temp;
    }

    public Address getById(Long id) {
        List<Address> addresses;
        try {
            addresses = addressService.getAll();
            for (Address item : addresses) {
                Long ida = item.getId();
                if (ida.equals(id)) {
                    System.out.println("Success! This id exists");
                    return item;

                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        return null;
    }

    public void selectAllAddress() throws SQLException {
        System.out.println(addressService.getAll());
    }
}

