package employee.service.crud.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOWN")
    private String town;
    @Column(name = "STREET")
    private String street;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "POST_CODE", length = 25)
    private String postCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getId(), address.getId()) && Objects.equals(getTown(), address.getTown()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getCountry(), address.getCountry()) && Objects.equals(getPostCode(), address.getPostCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTown(), getStreet(), getCountry(), getPostCode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", country='" + country + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
