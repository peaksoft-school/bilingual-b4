package kg.peaksoft.bilingualb4.repositories;

import kg.peaksoft.bilingualb4.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
