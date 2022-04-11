package kg.peaksoft.bilingualb4.services.impl;

import kg.peaksoft.bilingualb4.dto.request.RoleRequest;
import kg.peaksoft.bilingualb4.dto.response.RoleResponse;
import kg.peaksoft.bilingualb4.mappers.editMapper.RoleEditMapper;
import kg.peaksoft.bilingualb4.mappers.viewMapper.RoleViewMapper;
import kg.peaksoft.bilingualb4.repositories.RoleRepository;
import kg.peaksoft.bilingualb4.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleEditMapper roleEditMapper;
    private final RoleViewMapper roleViewMapper;

    @Override
    public RoleResponse save(RoleRequest roleRequest) {
        return roleViewMapper.view(roleRepository.save(roleEditMapper.create(roleRequest)));
    }
}

