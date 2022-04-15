package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.dto.request.TypeRequest;
import kg.peaksoft.bilingualb4.dto.response.TypeResponse;
import kg.peaksoft.bilingualb4.exception.BadRequestException;
import kg.peaksoft.bilingualb4.exception.NotFoundException;
import kg.peaksoft.bilingualb4.mappers.editMapper.TypeEditMapper;
import kg.peaksoft.bilingualb4.mappers.viewMapper.TypeViewMapper;
import kg.peaksoft.bilingualb4.models.Type;
import kg.peaksoft.bilingualb4.models.Word;
import kg.peaksoft.bilingualb4.models.enums.QuestionType;
import kg.peaksoft.bilingualb4.models.enums.SingleAndMultiType;
import kg.peaksoft.bilingualb4.repositories.TypeRepository;
import kg.peaksoft.bilingualb4.services.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        String currentName = type.getName();
        String newName = typeRequest.getName();

        if (!currentName.equals(newName)){
            type.setName(newName);
        }
        Object currentSingleAndMultiType = type.getSingleAndMultiType();
        SingleAndMultiType newSingleAndMultiType = typeRequest.getSingleAndMultiType();

        if (!currentSingleAndMultiType.equals(newSingleAndMultiType)){
            type.setSingleAndMultiType(newSingleAndMultiType);
        }
        List<Word> currentListWord = type.getWordList();
        List<Word> newListWord = typeRequest.getWordList();

        if (!currentListWord.equals(newListWord)){
            type.setWordList(newListWord);
        }
        String currentAudio = type.getAudio();
        String  newAudio = typeRequest.getAudio();

        if (!currentAudio.equals(newAudio)){
            type.setAudio(newAudio);
        }
        Object currentNumberOfReplays = type.getNumberOfReplays();
        int newNumberOfReplays = typeRequest.getNumberOfReplays();

        if (!currentNumberOfReplays.equals(newNumberOfReplays)){
            type.setNumberOfReplays(newNumberOfReplays);
        }
        String currentUpload = type.getUpload();
        String newUpload = typeRequest.getUpload();

        if (!currentUpload.equals(newUpload)){
            type.setUpload(newUpload);
        }
        String currentPlay = type.getPlay();
        String newPlay = typeRequest.getPlay();

        if (!currentPlay.equals(newPlay)){
            type.setPlay(newPlay);
        }
        Object currentCorrectAnswer  = type.isCorrectAnswer();
        boolean newCorrectAnswer = typeRequest.isCorrectAnswer();

        if (!currentCorrectAnswer.equals(newCorrectAnswer)){
            type.setCorrectAnswer(newCorrectAnswer);
        }
        String currentRecord = type.getRecord();
        String newRecord = typeRequest.getRecord();

        if (!currentRecord.equals(newRecord)){
            type.setRecord(newRecord);
        }
        String currentUploadImage = type.getUploadImage();
        String newUploadImage = typeRequest.getUploadImage();

        if (!currentUploadImage.equals(newUploadImage)){
            type.setUploadImage(newUploadImage);
        }
        String currentQuestionStatement = type.getQuestionStatement();
        String newQuestionStatement = typeRequest.getQuestionStatement();

        if (!currentQuestionStatement.equals(newQuestionStatement)){
            type.setQuestionStatement(newQuestionStatement);
        }
        Object currentWordCounter = type.getWordCounter();
        int newWordCounter = typeRequest.getWordCounter();

        if (!currentWordCounter.equals(newWordCounter)){
            type.setWordCounter(newWordCounter);
        }
        String currentQuestionToThePassage = type.getQuestionToThePassage();
        String newQuestionToThePassage = typeRequest.getQuestionToThePassage();

        if (!currentQuestionToThePassage.equals(newQuestionToThePassage)){
            type.setQuestionToThePassage(newQuestionToThePassage);
        }
        String currentPassage = type.getPassage();
        String newPassage = typeRequest.getPassage();

        if (!currentPassage.equals(newPassage)){
            type.setPassage(newPassage);
        }
        String currentHighlightCorrectAnswer = type.getHighlightCorrectAnswer();
        String newHighlightCorrectAnswer = typeRequest.getHighlightCorrectAnswer();

        if (!currentHighlightCorrectAnswer.equals(newHighlightCorrectAnswer)){
            type.setHighlightCorrectAnswer(newHighlightCorrectAnswer);
        }
        return typeEditMapper.update(type,typeRequest);
    }
    private Type findById(Long id){
        return typeRepository.findById(id).orElseThrow(()-> new NotFoundException(
                String.format("Type with id = %s does not exists",id)
        ));
    }
}
