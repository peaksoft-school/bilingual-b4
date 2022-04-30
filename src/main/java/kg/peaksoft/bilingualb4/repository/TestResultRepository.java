package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.model.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}