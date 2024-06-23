package pe.edu.cibertec.apirestventascibertec.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apirestventascibertec.model.bd.Order;
import pe.edu.cibertec.apirestventascibertec.repository.OrderRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private OrderRepository orderRepository;

    public List<Order> listarOrdenes(){
        return orderRepository.findAll();
    }
}
