package kg.peaksoft.bilingualb4.api.payload;

import kg.peaksoft.bilingualb4.model.entity.Options;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
public class UsersAnswerRequest {

    @Enumerated(EnumType.STRING)
    private SingleAndMultiType singleAndMultiType;
    private List<Options> optionsList;
    private String playButtonOfAudio;
    private String someText;
    private int numberOfReplays;
    private String image;
    private String record;
    private String textOfRecord;
    private int counterOfWord;




}
