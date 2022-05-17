package com.lc.apilc.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.lc.apilc.models.response.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("file")
public class UploadController {

    @Autowired
    private AmazonS3 s3;

    @Autowired
    @Qualifier("awsBucket")
    private String bucketName;

    @PostMapping
    public UploadResponse uploadFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
        File modifiedFile = new File(file.getOriginalFilename());
        FileOutputStream os = new FileOutputStream(modifiedFile);
        os.write(file.getBytes());

        String fileName = file.getOriginalFilename() + "_" + System.currentTimeMillis();
        s3.putObject(bucketName, fileName, modifiedFile);
        modifiedFile.delete();
        return UploadResponse.builder().message("Arquivo enviado com sucesso!").fileName(fileName).build();
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> dowloadFile(@RequestParam(name = "file") String fileName) throws IOException {
        S3Object s3Object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream objectContent = s3Object.getObjectContent();
        byte[] byteArray = IOUtils.toByteArray(objectContent);

        ByteArrayResource resource = new ByteArrayResource(byteArray);

        return ResponseEntity.ok()
                .contentLength(byteArray.length)
                .header("content-type", "application/octet-stream")
                .header("content-disposition", "attachment;fileName=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping
    public UploadResponse deleteFile(@RequestParam(name = "file") String fileName) {
        s3.deleteObject(bucketName, fileName);
        return UploadResponse.builder().message("Arquivo deletado com sucesso!").fileName(fileName).build();
    }

}
