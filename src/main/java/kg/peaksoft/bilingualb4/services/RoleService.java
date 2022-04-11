package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.dto.request.RoleRequest;
import kg.peaksoft.bilingualb4.dto.response.RoleResponse;

public interface RoleService {

    RoleResponse save(RoleRequest roleRequest);
}
