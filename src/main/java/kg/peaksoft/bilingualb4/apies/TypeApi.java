package kg.peaksoft.bilingualb4.apies;

import antlr.collections.impl.LList;
import kg.peaksoft.bilingualb4.dto.request.TypeRequest;
import kg.peaksoft.bilingualb4.dto.response.TypeResponse;
import kg.peaksoft.bilingualb4.mappers.editMapper.TypeEditMapper;
import kg.peaksoft.bilingualb4.mappers.viewMapper.TypeViewMapper;
import kg.peaksoft.bilingualb4.models.Type;
import kg.peaksoft.bilingualb4.services.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type")
public class TypeApi {

    private final TypeService typeService;
    private final TypeViewMapper typeViewMapper;

    @GetMapping
    public List<TypeResponse> findAll(){
        return typeService.findAll();
    }
    @PostMapping("/save")
    public TypeResponse save(@RequestBody TypeRequest typeRequest){
        return typeService.save(typeRequest);
    }

    @GetMapping("/find_by_id_or_name")
    public TypeResponse findByIdAndName(@RequestParam (required = false) Long id,
                                        @RequestParam (required = false) String name){
        return typeService.findByIdAndName(id,name);
    }

    @DeleteMapping("/delete_by_id/{id}")
    public List<TypeResponse> deleteById(@PathVariable Long id){
        typeService.deleteById(id);
        return typeService.findAll();
    }

    @PutMapping("/update_by_id/{id}")
    public TypeResponse updateById(@PathVariable Long id,
                                   @RequestBody TypeRequest typeRequest){
        Type type = typeService.updateById(id, typeRequest);
        return typeViewMapper.view(type);
    }
}
