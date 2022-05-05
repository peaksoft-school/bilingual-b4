package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.EvaluateResponse;
import kg.peaksoft.bilingualb4.model.entity.QuestionResult;

public interface EvaluateService {

    EvaluateResponse autoCheck(Long questionId, Long userAnswerId);

    EvaluateResponse manualChek(Long questionId, Long userAnswerId, QuestionResult questionResult);

    EvaluateResponse methodForHighlightType(Long  questionId, Long userAnswerId);
}
