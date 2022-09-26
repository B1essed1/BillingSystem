package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Admins;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Long> {

    Optional<Admins> findAdminsByUsername(String username);
}
