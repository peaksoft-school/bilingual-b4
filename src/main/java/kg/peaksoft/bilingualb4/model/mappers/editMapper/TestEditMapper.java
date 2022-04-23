package kg.peaksoft.bilingualb4.model.mappers.editMapper;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.model.entity.Test;
import org.springframework.stereotype.Component;

@Component
public class TestEditMapper {

    public Test create(TestRequest testRequest) {
        if (testRequest == null) {
            return null;
        }
        Test test = new Test();
        test.setTitle(testRequest.getTitle());
        test.setShortDescription(testRequest.getShortDescription());
        return test;
    }
}
