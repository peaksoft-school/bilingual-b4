package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {

    @Query("select case when count(c) > 0 then true else false end from Test c where c.title =?1")
    boolean existsByName(String title);

    Test findByTitle(String name);

    @Query("from Test t where t.isEnabled=true")
    List<Test> findAll();


}
