package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {

    @Query("select case when count(c) > 0 then true else false end from Test c where c.title =?1")
    boolean existsByName(String title);

    Test findByTitle(String name);
}
