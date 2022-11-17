package shakh.billingsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.models.DebitorsDto;
import shakh.billingsystem.models.DebitorsRegDto;

import java.util.Optional;

@Repository
public interface DebitorsRepository extends JpaRepository<Debitors, Long> {
    Optional<Debitors> findById(Debitors debitors);

    @Query("select new shakh.billingsystem.models.DebitorsDto(d.id,d.name,d.lastName ,d.phoneNumber,d.debt,d.additionalDetail) from Debitors d where d.debt>0")
    Optional<Page<DebitorsDto>>  findDebitorsByDebt(Pageable pageable);
}
