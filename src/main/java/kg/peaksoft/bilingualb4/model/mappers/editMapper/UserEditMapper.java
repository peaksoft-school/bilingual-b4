package kg.peaksoft.bilingualb4.model.mappers.editMapper;

import kg.peaksoft.bilingualb4.api.payload.UserRequest;
import kg.peaksoft.bilingualb4.model.entity.AuthInfo;
import kg.peaksoft.bilingualb4.model.entity.User;
import kg.peaksoft.bilingualb4.repository.RoleRepository;
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
}
