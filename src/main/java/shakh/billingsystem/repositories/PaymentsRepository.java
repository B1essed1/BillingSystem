package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Payments;

@Repository
public interface PaymentsRepository  extends JpaRepository<Payments,Long> {



}
