package pe.edu.cibertec.apirestventascibertec.model.bd;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer productid;
    @Column(name = "productname")
    private String productname;
    @Column(name = "quantityperunit")
    private String quantityperunit;
    @Column(name = "unitprice")
    private Double unitprice;
    @Column(name = "unitsinstock")
    private Integer unitsinstock;
    @Column(name = "unitsonorder")
    private Integer unitsonorder;
    @Column(name = "reorderlevel")
    private Integer reorderlevel;
    @Column(name = "discontinued")
    private Boolean discontinued;
    @ManyToOne
    @JoinColumn(name = "supplierid")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<OrderDetail> orders = new HashSet<>();


}
