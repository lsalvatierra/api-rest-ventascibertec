package pe.edu.cibertec.apirestventascibertec.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apirestventascibertec.model.bd.Category;
import pe.edu.cibertec.apirestventascibertec.model.bd.Product;
import pe.edu.cibertec.apirestventascibertec.model.bd.Supplier;
import pe.edu.cibertec.apirestventascibertec.model.request.ProductRequest;
import pe.edu.cibertec.apirestventascibertec.repository.ProductRepository;


import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> listarProductos(){
        return productRepository.findAll();
    }

    public Product guardarProducto(ProductRequest product){
        Product objProduct = new Product();
        if(product.getProductid() > 0){
            objProduct.setProductid(product.getProductid());
        }
        objProduct.setProductname(product.getProductname());
        objProduct.setUnitprice(product.getUnitprice());
        Boolean discontinued = false;
        if(product.getDiscontinued() != null){
             discontinued = product.getDiscontinued();
        }
        objProduct.setDiscontinued(discontinued);
        Category category = new Category();
        category.setCategoryid(product.getCategoryid());
        Supplier supplier = new Supplier();
        supplier.setSupplierid(product.getSupplierid());
        objProduct.setCategory(category);
        objProduct.setSupplier(supplier);
        return productRepository.save(objProduct);
    }

}
