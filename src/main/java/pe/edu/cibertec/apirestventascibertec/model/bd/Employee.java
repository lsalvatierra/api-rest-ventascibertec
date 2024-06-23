package pe.edu.cibertec.apirestventascibertec.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeid;
    private String lastname;
    private String firstname;
    private String title;
    private String titleofcourtesy;
    private Date birthdate;
    private Date hiredate;
    private String address;
    private String city;
    private String region;
    private String postalcode;
    private String country;
    private String homephone;
    private String extension;
    private String notes;
    private Integer reportsto;
    private String photopath;
    private Double salary;
}
