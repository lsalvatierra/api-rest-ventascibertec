package pe.edu.cibertec.apirestventascibertec.model.bd;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pe.edu.cibertec.apirestventascibertec.model.bd.pk.ProductOrderId;

@Getter
@Setter
@Entity
@Table(name = "orderdetails")
public class OrderDetail {
    @EmbeddedId
    private ProductOrderId id;

    private Double unitprice;
    private Integer quantity;
    private Double discount;

    @ManyToOne
    @MapsId("productid")
    @JoinColumn(name = "productid")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @MapsId("orderid")
    @JoinColumn(name = "orderid")
    @JsonBackReference
    private Order order;
}
