package entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "PHONE_NUMBER")
    private String phone_number;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "POSITION")
    private String position;


    @Column(name = "EMPLOYMENT_DATA")
    private LocalDate employment_data;


    @Column(name = "DATA_OF_DISMISSAL")
    private LocalDate data_of_dismissal;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "department_id")
    private Department department;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Employee employee)) return false;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(birthday, employee.birthday) && Objects.equals(phone_number, employee.phone_number) && Objects.equals(address, employee.address) && Objects.equals(position, employee.position) && Objects.equals(employment_data, employee.employment_data) && Objects.equals(data_of_dismissal, employee.data_of_dismissal) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, phone_number, address, position, employment_data, data_of_dismissal, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", phone_number='" + phone_number + '\'' +
                ", address=" + address +
                ", position='" + position + '\'' +
                ", employment_data=" + employment_data +
                ", data_of_dismissal=" + data_of_dismissal +
                ", department=" + department +
                '}';
    }
}
