package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Debitors;

import java.util.Optional;

@Repository
public interface DebitorsRepository extends JpaRepository<Debitors, Long> {
    Optional<Debitors> findById(Debitors debitors);
}
