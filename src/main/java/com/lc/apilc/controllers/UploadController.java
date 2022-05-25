package com.lc.apilc.controllers;

import com.lc.apilc.models.response.UploadResponse;
import com.lc.apilc.services.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("file")
public class UploadController {

    @Autowired
    private AwsService awsService;

    @PostMapping
    public UploadResponse uploadFile(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "acl") String acl) throws IOException {
        String uploadedFile = awsService.uploadFile(file, acl);
        return UploadResponse.builder().message("Arquivo enviado com sucesso!").fileName(uploadedFile).build();
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> dowloadFile(@RequestParam(name = "file") String fileName) throws IOException {
        byte[] byteArray = awsService.dowloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(byteArray);
        return ResponseEntity.ok()
                .contentLength(byteArray.length)
                .header("content-type", "application/octet-stream")
                .header("content-disposition", "attachment;fileName=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping
    public UploadResponse deleteFile(@RequestParam(name = "file") String fileName) {
        awsService.deleteFile(fileName);
        return UploadResponse.builder().message("Arquivo deletado com sucesso!").fileName(fileName).build();
    }

}
