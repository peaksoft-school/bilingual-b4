package kg.peaksoft.bilingualb4.mappers.viewMapper;

import kg.peaksoft.bilingualb4.dto.request.RoleRequest;
import kg.peaksoft.bilingualb4.dto.response.RoleResponse;
import kg.peaksoft.bilingualb4.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleViewMapper {

    public RoleResponse view(Role role) {
        if (role == null) {
            return null;
        }
        RoleResponse roleResponse = new RoleResponse();
        if (role.getId() != null) {
            roleResponse.setId(String.valueOf(role.getId()));
        }
        roleResponse.setName(role.getName());
        return roleResponse;

    }
}
