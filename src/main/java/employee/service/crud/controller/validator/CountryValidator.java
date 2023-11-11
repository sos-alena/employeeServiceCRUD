package employee.service.crud.controller.validator;

import java.util.Locale;

public class CountryValidator {

    public static String getNameCountry() {
        String name = InputValue.inputValidateStr();

        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);
            String country = obj.getDisplayCountry(Locale.US);
            if (country.equals(name)) {
                System.out.println("Country Name = " + obj.getDisplayCountry(Locale.US));
                return name;
            }

        }
        System.out.println("Error input");
        return getNameCountry();
    }
}
