package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}
