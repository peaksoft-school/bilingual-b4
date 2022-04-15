package kg.peaksoft.bilingualb4.repositories;

import kg.peaksoft.bilingualb4.dto.response.TypeResponse;
import kg.peaksoft.bilingualb4.models.Type;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("select case when count(c) > 0 then true else false end from Type c where c.name =?1")
    boolean existsByName(String name);

    @Query("select c from Type c where c.name =?1")
    Optional<Type> findByName(String name);

    @Query("select  q from Type q where q.questionType =?1")
    List<Type> findAllByQuestionType(QuestionType questionType);

}