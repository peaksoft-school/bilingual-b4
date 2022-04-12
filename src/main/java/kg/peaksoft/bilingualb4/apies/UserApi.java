package kg.peaksoft.bilingualb4.apies;

import kg.peaksoft.bilingualb4.dto.request.UserRequest;
import kg.peaksoft.bilingualb4.dto.response.UserResponse;
import kg.peaksoft.bilingualb4.models.User;
import kg.peaksoft.bilingualb4.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class UserApi {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/save")
    public UserResponse save(@PathVariable("id") Long id,
                             @RequestBody UserRequest userRequest) {
        return userService.save(id, userRequest);
    }

   @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @DeleteMapping("/delete/{deleteId}")
    public void deleteBy(@PathVariable("deleteId") Long id) {
        userService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @GetMapping("/find/{findById}")
    public Optional<User> findById(@PathVariable("findById") Long id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @PatchMapping("/update/{updateId}")
    public UserResponse update(@PathVariable("updateId") Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }


}
