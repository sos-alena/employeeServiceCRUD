package entityController;

import entity.Address;
import service.AddressService;

import java.sql.SQLException;

import static validator.InputValue.inputValidateStr;

public class AddressController {

    AddressService addressService = new AddressService();
    public Address inputAddress() throws SQLException {
        Address address = new Address();
        System.out.println("Enter country");
        String country = inputValidateStr();
        address.setCountry(country);
        System.out.println("Enter town");
        String town = inputValidateStr();
        address.setTown(town);
        System.out.println("street");
        String street = inputValidateStr();
        address.setStreet(street);
        System.out.println("postCode");
      String postCode = inputValidateStr();
      address.setPostCode(postCode);

       addressService.add(address);

       return address;
    }
}
