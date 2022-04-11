package kg.peaksoft.bilingualb4.apies;

import kg.peaksoft.bilingualb4.dto.request.RoleRequest;
import kg.peaksoft.bilingualb4.dto.response.RoleResponse;
import kg.peaksoft.bilingualb4.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleApi {

    private final RoleService roleService;

    @PostMapping("/save")
    public RoleResponse save(@RequestBody RoleRequest roleRequest){
        return roleService.save(roleRequest);
    }
}
