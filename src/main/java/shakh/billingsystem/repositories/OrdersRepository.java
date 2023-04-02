package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Orders;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "select o from Orders o where o.totalCost-o.paidCost > 0 and o.debitors.id =:id and o.isDeleted = false")
     List<Orders> findUnpaidOrder(@Param("id") Long id);
}
