package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
