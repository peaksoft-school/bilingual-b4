package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.model.entity.MyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyResultRepository extends JpaRepository<MyResult, Long> {

    @Query("select case when count(c) > 0 then true else false end from MyResult c where c.user.id =?1")
    boolean existsByUserId(Long userId);

    @Query("from MyResult c where c.user.id=?1")
    List<MyResult> findAllByUserId(Long userId);

    @Query("from MyResult c where c.user.id =?1")
    MyResult findByUserId(Long id);
}