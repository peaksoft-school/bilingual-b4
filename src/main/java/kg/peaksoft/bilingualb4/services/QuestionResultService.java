package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.QuestionResultRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResultResponse;

import java.util.List;

public interface QuestionResultService {

    List<QuestionResultResponse> findAll(Long id);

    QuestionResultResponse findById(Long id);

    QuestionResultResponse updateById(Long id, QuestionResultRequest questionResultRequest);

    void sendResultsToUser(Long id);

}
