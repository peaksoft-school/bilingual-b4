package kg.peaksoft.bilingualb4.apies;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.bilingualb4.dto.request.UserRequest;
import kg.peaksoft.bilingualb4.dto.response.UserResponse;
import kg.peaksoft.bilingualb4.models.User;
import kg.peaksoft.bilingualb4.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "User:", description = "Quote CRUD operations")
public class UserApi {

    private final UserService userService;

    @Operation(summary = "Gets a list of users: workshop$User",
            description = "Returns a map of status codes to quantities:")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users", content =
                    {@Content(mediaType = "application/json", array = @ArraySchema(schema =
                    @Schema(implementation = UserApi.class)))})})
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @Operation(summary = "Creates new entity: workshop$User", description = """
            The method expects a JSON with entity object in the request body.
            The entity object may contain references to other entities
                        
            The following fields may be provided:
                        
            name (String)
                        
             email (String)
                        
             password (String)
            """)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/registration")
    public UserResponse registration(@RequestBody UserRequest userRequest) {
        return userService.registration(userRequest);
    }

    @Operation(summary = "Deletes the entity: workshop$User",
            description = "Deletes an endpoint and all its child entities.")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteBy(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @Operation(summary = "Gets a single entity by identifier: workshop$User",
            description = "For valid response try integer IDs with value >= 1 and:")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("getBy/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Updates the entity: workshop$User", description = """
            Updates the details of an endpoint.
                        
             You can provide following fields with this request:
                        
             name (String)
                        
             email (String)
                        
             password (String)
            """)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }
}
