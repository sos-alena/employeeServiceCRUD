package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESS")
public class Address{

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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Address address)) return false;
        return Objects.equals(id, address.id) && Objects.equals(town, address.town) && Objects.equals(street, address.street) && Objects.equals(country, address.country) && Objects.equals(postCode, address.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, town, street, country, postCode);
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
