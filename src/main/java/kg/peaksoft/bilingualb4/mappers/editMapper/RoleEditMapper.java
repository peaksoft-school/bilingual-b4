package kg.peaksoft.bilingualb4.mappers.editMapper;

import kg.peaksoft.bilingualb4.dto.request.RoleRequest;
import kg.peaksoft.bilingualb4.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleEditMapper {

    public Role create(RoleRequest request) {
        if (request == null) {
            return null;
        }
        Role role = new Role();
        role.setName(request.getName());
        return role;
    }
}
