package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.dto.request.QuestionRequest;
import kg.peaksoft.bilingualb4.dto.response.QuestionResponse;
import kg.peaksoft.bilingualb4.models.Question;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;

import java.util.List;

public interface QuestionService {

    List<Question> findAll(QuestionType questionType);

    QuestionResponse save(QuestionRequest questionRequest);

    QuestionResponse findByIdAndName(Long id, String name);

    void deleteById(Long id);

    Question updateById(Long id, QuestionRequest questionRequest);

}
