package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.model.entity.UsersAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersAnswerRepository extends JpaRepository<UsersAnswer, Long> {

    @Query("from UsersAnswer c where c.question.test.id =:id ")
    List<UsersAnswer> findAllByTestId(Long id);
}
