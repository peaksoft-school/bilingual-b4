package kg.peaksoft.bilingualb4.api.payload;

import kg.peaksoft.bilingualb4.model.entity.Question;
import kg.peaksoft.bilingualb4.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestRequest {

    private String title;
    private String shortDescription;
    private List<Question>questionList;

}
