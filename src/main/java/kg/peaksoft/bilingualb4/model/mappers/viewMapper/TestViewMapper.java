package kg.peaksoft.bilingualb4.model.mappers.viewMapper;

import kg.peaksoft.bilingualb4.api.payload.TestResponse;
import kg.peaksoft.bilingualb4.model.entity.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestViewMapper {

    public TestResponse view(Test test) {
        if (test == null) {
            return null;
        }
        TestResponse testResponse = new TestResponse();
        if (test.getId() != null) {
            testResponse.setId(String.valueOf(test.getId()));
        }
        testResponse.setTitle(test.getTitle());
        testResponse.setShortDescription(test.getShortDescription());
        return testResponse;

    }

    public List<TestResponse> view(List<Test> tests) {
        List<TestResponse> testResponses = new ArrayList<>();
        for (Test c : tests
        ) {
            testResponses.add(view(c));
        }
        return testResponses;
    }
}
