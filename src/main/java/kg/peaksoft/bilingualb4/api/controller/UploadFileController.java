package kg.peaksoft.bilingualb4.api.controller;

import kg.peaksoft.bilingualb4.services.AwsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/file")
@AllArgsConstructor
public class UploadFileController {

    private AwsService awsService;

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        String publicURL = awsService.uploadFile(file);
        Map<String, String> response = new HashMap<>();
        response.put("publicURL", publicURL);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED );
    }

    @DeleteMapping
    public String downloadFile(@RequestParam String file){
         return awsService.delete(file);
    }
}