package kg.peaksoft.bilingualb4.model.mappers.editMapper;

import kg.peaksoft.bilingualb4.api.payload.TestRequest;
import kg.peaksoft.bilingualb4.model.entity.Test;
import kg.peaksoft.bilingualb4.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TestEditMapper {

    private final UserRepository userRepository;

    public TestEditMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Test create(TestRequest testRequest) {
        if (testRequest == null) {
            return null;
        }
        Test test = new Test();
        test.setTitle(testRequest.getTitle());
        test.setShortDescription(testRequest.getShortDescription());
        test.setQuestionList(testRequest.getQuestionList());
//        test.setUser1(userRepository.getById(id));
     return test;
    }

    public void Update(Test test, TestRequest testRequest) {
        test.setTitle(testRequest.getTitle());
        test.setShortDescription(testRequest.getShortDescription());
    }
}
