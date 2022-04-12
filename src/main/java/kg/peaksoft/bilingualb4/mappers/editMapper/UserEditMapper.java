package kg.peaksoft.bilingualb4.mappers.editMapper;

import kg.peaksoft.bilingualb4.dto.request.UserRequest;
import kg.peaksoft.bilingualb4.models.AuthInfo;
import kg.peaksoft.bilingualb4.models.User;
import kg.peaksoft.bilingualb4.repositories.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper {

    private final RoleRepository roleRepository;

    public UserEditMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public User create(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(userRequest.getEmail());
        authInfo.setPassword(user.getPassword());
        authInfo.setAuthInfo1(roleRepository.getByName("CLIENT"));

        user.setAuthInfo(authInfo);
        return user;
    }

    public void Update(User user, UserRequest userRequest) {
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
    }

}
