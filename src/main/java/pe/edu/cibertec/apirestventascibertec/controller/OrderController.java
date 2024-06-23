package pe.edu.cibertec.apirestventascibertec.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.apirestventascibertec.model.bd.Order;
import pe.edu.cibertec.apirestventascibertec.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<Order>> listarOrdenes(){
        List<Order> productList = new ArrayList<>(orderService.listarOrdenes());
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
