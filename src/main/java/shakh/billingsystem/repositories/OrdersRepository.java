package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
