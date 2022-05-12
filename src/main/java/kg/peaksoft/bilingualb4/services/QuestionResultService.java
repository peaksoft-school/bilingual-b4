package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultResponse;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;

import java.util.List;

public interface QuestionResultService {

    List<QuestionResultResponse> findAll();

    QuestionResultResponse findById(Long id);

    EvaluateResponse updateById(Long id, QuestionResultRequest questionResultRequest);

    String sendResultsToUser(Long id);

}
