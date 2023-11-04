package pe.edu.cibertec.apirestventascibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.apirestventascibertec.model.bd.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
