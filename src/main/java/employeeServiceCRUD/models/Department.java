package employeeServiceCRUD.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
public class Department{


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "TITLE")
        private String title;
        @OneToMany(mappedBy = "department")
        List<Employee> employees;

        @Override
        public boolean equals(Object object) {
                if (this == object) return true;
                if (!(object instanceof Department that)) return false;
                return Objects.equals(id, that.id) && Objects.equals(title, that.title);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title);
        }

        @Override
        public String toString() {
                return "Department{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        '}';
        }
}
