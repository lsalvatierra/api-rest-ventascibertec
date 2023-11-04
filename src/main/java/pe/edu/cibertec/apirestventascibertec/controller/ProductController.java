package pe.edu.cibertec.apirestventascibertec.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.apirestventascibertec.model.bd.Category;
import pe.edu.cibertec.apirestventascibertec.model.bd.Product;
import pe.edu.cibertec.apirestventascibertec.model.dto.DtoEntity;
import pe.edu.cibertec.apirestventascibertec.model.dto.ProductDto;
import pe.edu.cibertec.apirestventascibertec.service.ProductService;
import pe.edu.cibertec.apirestventascibertec.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> listarProductos(){
        List<Product> productList = new ArrayList<>();
        productService.listarProductos()
                .forEach(productList::add);
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarProductosDto(){
        List<DtoEntity> productList = new ArrayList<DtoEntity>();
        productList =productService.listarProductos()
                .stream()
                .map(x -> new DtoUtil().convertirADto(x, new ProductDto()))
                .collect(Collectors.toList());
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
