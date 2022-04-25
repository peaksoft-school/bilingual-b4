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
    @JsonProperty("single_and_multi_type")
    private SingleAndMultiType singleAndMultiType;
    @JsonProperty("option_list")
    private List<Options> optionsList;
    @JsonProperty("play_button_of_audio")
    private String playButtonOfAudio;
    @JsonProperty("some_text")
    private String someText;
    @JsonProperty("number_of_replays")
    private int numberOfReplays;
    private String image;
    private String record;
    @JsonProperty("text_of_record")
    private String textOfRecord;
    @JsonProperty("counter_of_word")
    private int counterOfWord;
}
