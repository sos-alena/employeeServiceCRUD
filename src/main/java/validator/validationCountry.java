package validator;

import java.util.Locale;

import static validator.InputValue.inputValidateStr;

public class validationCountry {

    public static String getNameCountry() {
        System.out.println("Input COUNTRY: ");
        String name = inputValidateStr();

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
    public void getAllName() {

        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);

            System.out.println(" Country Name = " + obj.getDisplayCountry(Locale.US));

        }

    }
}
