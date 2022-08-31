package shakh.billingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.entities.Unload;

@Repository
public interface UnloadRepository extends JpaRepository<Unload,Long> {
}
