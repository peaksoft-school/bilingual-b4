package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.MyResultResponse;

import java.util.List;

public interface MyResultService {

    MyResultResponse saveUserResult(Long id);

    MyResultResponse deleteUserResultById(Long id);

    List<MyResultResponse> findAll();
}
