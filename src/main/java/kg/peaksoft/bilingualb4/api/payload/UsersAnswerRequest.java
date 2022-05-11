package kg.peaksoft.bilingualb4.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.bilingualb4.model.entity.Option;
import kg.peaksoft.bilingualb4.model.enums.SingleAndMultiType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class UsersAnswerRequest {

    @Enumerated(EnumType.STRING)
    @JsonProperty("single_and_multi_type")
    private SingleAndMultiType singleAndMultiType;
    @JsonProperty("option_list")
    private List<Option> optionList;
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
