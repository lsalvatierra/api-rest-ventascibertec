package pe.edu.cibertec.apirestventascibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.apirestventascibertec.model.bd.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
