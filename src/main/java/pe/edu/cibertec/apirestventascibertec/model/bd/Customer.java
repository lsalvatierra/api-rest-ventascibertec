package pe.edu.cibertec.apirestventascibertec.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String customerid;
    private String companyname;
    private String contactname;
    private String contacttitle;
    private String address;
    private String city;
    private String region;
    private String postalcode;
    private String country;
    private String phone;
    private String fax;
}
