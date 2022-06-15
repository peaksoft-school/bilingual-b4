package kg.peaksoft.bilingualb4.repository;

import kg.peaksoft.bilingualb4.model.entity.MyResult;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;
import kg.peaksoft.bilingualb4.model.entity.TestResult;
import kg.peaksoft.bilingualb4.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyResultRepository extends JpaRepository<MyResult, Long> {

    @Query("select case when count(c) > 0 then true else false end from MyResult c where c.user.id =?1")
    boolean existsByTestName(Long id);

    @Query("from MyResult c where c.user.id=?1 and c.status =?2 order by c.dateOfSubmission")
    List<MyResult> findAllByUserId(Long userId, Status status);

    @Query("from MyResult c where c.user.id =?1")
    MyResult findByUserId(Long id);

    @Query("from MyResult c where c.status =?1 order by c.dateOfSubmission")
    List<MyResult> findAllByActive(Status status);

    @Query("select c from QuestionResult c where c.question.id=:id")
    List<MyResult> findAllByQuestionId(Long id);

    @Modifying
    @Query("delete from MyResult m where m.user.id =:userId")
    void deleteMyResultByUserId(Long userId);

}