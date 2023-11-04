package pe.edu.cibertec.apirestventascibertec.model.dto;

import lombok.*;

@Data
public class ProductDto implements DtoEntity {
    private Integer productid;
    private String productname;
    private String quantityperunit;
    private Double unitprice;
    private CategoryDto category;
    private SupplierDto supplier;
}
