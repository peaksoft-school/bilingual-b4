package kg.peaksoft.bilingualb4.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.bilingualb4.api.payload.QuestionRequest;
import kg.peaksoft.bilingualb4.api.payload.QuestionResponse;
import kg.peaksoft.bilingualb4.model.mappers.viewMapper.QuestionViewMapper;
import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.model.enums.QuestionType;
import kg.peaksoft.bilingualb4.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Question: ", description = "Quote CRUD operations")
@RequestMapping("/api/question")
public class QuestionApi {

    private final QuestionService questionService;
    private final QuestionViewMapper questionViewMapper;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Question",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = QuestionApi.class)))})})
    @Operation(
            summary = "Get a list of entity: workshop Question",
            description = "Returns a map of status codes to quantities:")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<Question> findAll(@RequestParam QuestionType questionType) {
        return questionService.findAll(questionType);
    }

    //    @Operation(
//            summary = "Creates new entity: workshop$Question",
//            description = """
//                    The method expects a JSON with entity object in the request body.
//                    The entity object may contain references to other entities.
//
//
//                    If you want to create new entity with type {SELECT_REAL_ENGLISH_WORD}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "single_and_multi_type":(enum(SINGLE,MULTI))
//                        "word_list":[
//                            "wordName":(String)
//                            "correctAnswer":boolean(default:false)
//                             ]
//
//                    If you want to create new entity with type {LISTEN_AND_SELECT_WORD}
//                    you should fill in the following fields:
//                        "audio":(String)
//                        "single_and_multi_type":(enum(SINGLE,MULTI))
//
//                    If you want to create new entity with type {TYPE_WHAT_YOU_HEAR}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "number_of_replays": (int)
//                        "upload":(String)
//                        "play":(String)
//                        "correct_answer": boolean(default:false)
//
//                    If you want to create new entity with type {RECORD_SAYING_STATEMENT}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "record":(String)
//
//                    If you want to create new entity with type {DESCRIBE_IMAGE}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "upload_image":(String)
//                        "correct_answer":boolean(default:false)
//
//                    If you want to create new entity with type {RESPOND_IN_AT_LEAST_N_WORDS}
//                    you should fill in the following fields:
//                        "name": (String)
//                        "question_statement": (String)
//                        "word_counter": (int)
//
//                    If you want to create new entity with type {HIGHLIGHT_THE_ANSWER}
//                    you should fill in the following fields:
//                        "question_to_the_passage": (String)
//                        "passage": (String)
//                        "highlight_correct_answer": (String)
//
//                    If you want to create new entity with type {SELECT_MAIN_IDEA}
//                    you should fill in the following fields:
//                        "name": (String)
//                        "passage":(String)
//                        "single_and_multi_type":(enum(SINGLE,MULTI))
//                        "word_list":[
//                            "wordName":(String)
//                            "correctAnswer":boolean(default:false)
//                            ]
//                    """)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}")
    public QuestionResponse save(@RequestBody QuestionRequest questionRequest, @PathVariable("id") Long id) {
        return questionService.save(id, questionRequest);
    }

    @Operation(
            summary = "Gets a single entity by identifier: workshop$Question",
            description = "For valid response try integer IDs with value >= 1 and:")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/find_by_id_or_name")
    public QuestionResponse findByIdAndName(@RequestParam(required = false) Long id,
                                            @RequestParam(required = false) String name) {
        return questionService.findByIdAndName(id, name);
    }

    @Operation(
            summary = "Deletes the entity: workshop$Question",
            description = "Deletes an endpoint and all its child entities.")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        questionService.deleteById(id);
    }

    //    @Operation(
//            summary = "Updates the entity: workshop$Question",
//            description = """
//                    The method expects a JSON with entity object in the request body.
//                    The entity object may contain references to other entities.
//
//
//                    If you want to update the entity with type {SELECT_REAL_ENGLISH_WORD}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "single_and_multi_type":(enum(SINGLE,MULTI))
//                        "word_list":[
//                            "wordName":(String)
//                            "correctAnswer":boolean(default:false)
//                             ]
//
//                    If you want to update the entity with type {LISTEN_AND_SELECT_WORD}
//                    you should fill in the following fields:
//                        "audio":(String)
//                        "single_and_multi_type":(enum(SINGLE,MULTI))
//
//                    If you want to update the entity with type {TYPE_WHAT_YOU_HEAR}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "number_of_replays": (int)
//                        "upload":(String)
//                        "play":(String)
//                        "correct_answer": boolean(default:false)
//
//                    If you want to update the entity with type {RECORD_SAYING_STATEMENT}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "record":(String)
//
//                    If you want to update the entity with type {DESCRIBE_IMAGE}
//                    you should fill in the following fields:
//                        "name":(String)
//                        "upload_image":(String)
//                        "correct_answer":boolean(default:false)
//
//                    If you want to update the entity with type {RESPOND_IN_AT_LEAST_N_WORDS}
//                    you should fill in the following fields:
//                        "name": (String)
//                        "question_statement": (String)
//                        "word_counter": (int)
//
//                    If you want to update the entity with type {HIGHLIGHT_THE_ANSWER}
//                    you should fill in the following fields:
//                        "question_to_the_passage": (String)
//                        "passage": (String)
//                        "highlight_correct_answer": (String)
//
//                    If you want to update the entity with type {SELECT_MAIN_IDEA}
//                    you should fill in the following fields:
//                        "name": (String)
//                        "passage":(String)
//                        "single_and_multi_type":(enum(SINGLE,MULTI))
//                        "word_list":[
//                            "wordName":(String)
//                            "correctAnswer":boolean(default:false)
//                            ]
//                    """)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public QuestionResponse updateById(@PathVariable Long id,
                                       @RequestBody QuestionRequest questionRequest) {
        System.out.println(questionRequest.getWordList().toString());
        Question question = questionService.updateById(id, questionRequest);
        return questionViewMapper.view(question);
    }
}
