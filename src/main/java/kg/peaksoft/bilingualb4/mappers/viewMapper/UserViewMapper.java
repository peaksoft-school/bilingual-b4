package kg.peaksoft.bilingualb4.mappers.viewMapper;

import kg.peaksoft.bilingualb4.dto.response.UserResponse;
import kg.peaksoft.bilingualb4.models.AuthInfo;
import kg.peaksoft.bilingualb4.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserViewMapper {

    public UserResponse view(User user) {
        if (user == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();
        if (user.getId() != null) {
            userResponse.setId(String.valueOf(user.getId()));
        }
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        return userResponse;

    }

    public List<UserResponse> view(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User c : users
        ) {
            userResponses.add(view(c));
        }
        return userResponses;
    }
}
