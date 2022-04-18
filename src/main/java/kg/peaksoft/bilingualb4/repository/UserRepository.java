package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
