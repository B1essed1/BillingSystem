package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.ReserveAdmin;

@Repository
public interface ReserveAdminRepository extends JpaRepository<ReserveAdmin,Long> {
    ReserveAdmin findReserveAdminByUsername(String username);
    ReserveAdmin findReserveAdminByEmail(String email);
}
