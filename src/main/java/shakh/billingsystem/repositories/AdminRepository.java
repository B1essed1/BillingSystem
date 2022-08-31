package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Admins;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Long> {

    Admins findAdminsByUsername(String username);
}
