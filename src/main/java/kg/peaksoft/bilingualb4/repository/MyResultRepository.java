package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.model.entity.MyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyResultRepository extends JpaRepository<MyResult, Long> {

    List<MyResult> findAllById(Long id);
}