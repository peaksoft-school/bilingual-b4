package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.api.payload.MyResultResponse;

import java.util.List;

public interface MyResultService {


    MyResultResponse findById(Long id);

    MyResultResponse deleteById(Long id);

    List<MyResultResponse> findAll(Long id);

}
