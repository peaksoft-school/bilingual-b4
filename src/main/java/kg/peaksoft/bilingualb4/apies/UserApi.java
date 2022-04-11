package kg.peaksoft.bilingualb4.apies;

import kg.peaksoft.bilingualb4.dto.request.UserRequest;
import kg.peaksoft.bilingualb4.dto.response.UserResponse;
import kg.peaksoft.bilingualb4.models.User;
import kg.peaksoft.bilingualb4.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class UserApi {

    private final UserService userService;

    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    // @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/save")
    public UserResponse save(@PathVariable("id") Long id,
                             @RequestBody UserRequest userRequest) {
        return userService.save(id, userRequest);
    }

    // @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{deleteId}")
    public void deleteBy(@PathVariable("deleteId") Long id) {
        userService.deleteById(id);
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/find/{findId}")
    public Optional<User> findById(@PathVariable("findId") Long id) {
        return userService.findById(id);
    }

    // @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/update/{updateId}")
    public UserResponse update(@PathVariable("updateId") Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

}
