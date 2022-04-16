package kg.peaksoft.bilingualb4.repositories;

import kg.peaksoft.bilingualb4.models.Question;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select case when count(c) > 0 then true else false end from Question c where c.name =?1")
    boolean existsByName(String name);

    @Query("select c from Question c where c.name =?1")
    Optional<Question> findByName(String name);

    @Query("select  q from Question q where q.questionType =?1")
    List<Question> findAllByQuestionType(QuestionType questionType);

}