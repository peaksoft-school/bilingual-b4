package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.dto.request.TypeRequest;
import kg.peaksoft.bilingualb4.dto.response.TypeResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.mappers.editMapper.TypeEditMapper;
import kg.peaksoft.bilingualb4.mappers.viewMapper.TypeViewMapper;
import kg.peaksoft.bilingualb4.models.Type;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;
import kg.peaksoft.bilingualb4.repositories.TypeRepository;
import kg.peaksoft.bilingualb4.services.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;


@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeEditMapper typeEditMapper;
    private final TypeViewMapper typeViewMapper;

    @Override
    public List<Type> findAll(QuestionType questionType) {
        return typeRepository.findAllByQuestionType(questionType);
    }

    @Override
    public TypeResponse save(TypeRequest typeRequest) {
        String name = typeRequest.getName();
        boolean exists = typeRepository.existsByName(name);

        if (exists) {
            throw new BadRequestException(
                    String.format("type with name = %s has already exists", name)
            );
        }
        Type type = typeEditMapper.create(typeRequest);
        Type save = typeRepository.save(type);
        return typeViewMapper.view(save);
    }

    @Override
    public TypeResponse findByIdAndName(Long id, String name) {
        int counter = id != null ? 1 : 0;
        counter += !isNullOrEmpty(name) ? 1 : 0;
        if (counter > 1) {
            throw new BadRequestException(
                    "You should to choose  only one field"
            );
        }
        if (id != null) {
            Type type = findById(id);
            return typeViewMapper.view(type);
        }
        if (!isNullOrEmpty(name)) {
            Type type = typeRepository.findByName(name).orElseThrow(() -> new NotFoundException(
                    String.format("Type with name = %s does not exists", name)
            ));
            return typeViewMapper.view(type);
        }
        throw new BadRequestException("You should write one of {id, name} to get Type");
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = typeRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(
                    String.format(
                            "Type with id %s does not exists", id
                    )
            );
        }
        typeRepository.deleteById(id);
    }

    @Override
    public Type updateById(Long id, TypeRequest  typeRequest) {
        Type type = findById(id);

        boolean exists = typeRepository.existsById(id);

        if (exists){
            throw new BadRequestException(
                    String.format("question with %d is already exists",id)
            );
        }else {
            type.setName(typeRequest.getName());
            type.setSingleAndMultiType(typeRequest.getSingleAndMultiType());
            type.setWordList(typeRequest.getWordList());
            type.setAudio(typeRequest.getAudio());
            type.setNumberOfReplays(typeRequest.getNumberOfReplays());
            type.setUpload(typeRequest.getUpload());
            type.setPlay(typeRequest.getPlay());
            type.setCorrectAnswer(typeRequest.isCorrectAnswer());
            type.setRecord(typeRequest.getRecord());
            type.setUploadImage(typeRequest.getUploadImage());
            type.setQuestionStatement(typeRequest.getQuestionStatement());
            type.setWordCounter(typeRequest.getWordCounter());
            type.setQuestionToThePassage(typeRequest.getQuestionToThePassage());
            type.setPassage(typeRequest.getPassage());
            type.setHighlightCorrectAnswer(typeRequest.getHighlightCorrectAnswer());

        }
        return type;
    }
    private Type findById(Long id){
        return typeRepository.findById(id).orElseThrow(()-> new NotFoundException(
                String.format("Type with id = %s does not exists",id)
        ));
    }
}
