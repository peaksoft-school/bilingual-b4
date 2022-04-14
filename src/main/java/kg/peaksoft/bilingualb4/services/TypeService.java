package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.dto.request.TypeRequest;
import kg.peaksoft.bilingualb4.dto.response.TypeResponse;
import kg.peaksoft.bilingualb4.models.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    List<TypeResponse> findAll();

    TypeResponse save(TypeRequest typeRequest);

    TypeResponse findByIdAndName(Long id,String name);

    void deleteById(Long id);

    Type updateById(Long id, TypeRequest typeRequest);

}
