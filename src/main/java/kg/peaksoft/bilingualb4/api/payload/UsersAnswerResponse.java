package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.entity.Options;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
@Builder
public class UsersAnswerResponse {

    private String id;
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
