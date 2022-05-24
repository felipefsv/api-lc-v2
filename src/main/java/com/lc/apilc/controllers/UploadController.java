package com.lc.apilc.controllers;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.lc.apilc.configuration.AWSConfig;
import com.lc.apilc.configuration.InternationalizationConfig;
import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.LcException;
import com.lc.apilc.models.response.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("file")
public class UploadController {

    @Autowired
    private AWSConfig awsConfig;

    @Autowired
    private InternationalizationConfig internationalizationConfig;

    @PostMapping
    public UploadResponse uploadFile(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "acl") String acl) throws IOException {
        File modifiedFile = new File(file.getOriginalFilename());
        FileOutputStream os = new FileOutputStream(modifiedFile);
        os.write(file.getBytes());

        String fileName = getFormattedFileName(file);

        CannedAccessControlList cannedAccessControlList;

        if (acl.equals("public")) {
            cannedAccessControlList = CannedAccessControlList.PublicRead;
        } else if (acl.equals("private")) {
            cannedAccessControlList = CannedAccessControlList.Private;
        } else {
            throw  new LcException("ACL Inválido!", ErrorCodes.ACL_INVALIDO);
        }

        awsConfig.getClient().putObject(new PutObjectRequest(awsConfig.getAwsBucket(), fileName, modifiedFile)
                .withCannedAcl(cannedAccessControlList));

        modifiedFile.delete();
        return UploadResponse.builder().message("Arquivo enviado com sucesso!").fileName(fileName).build();
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> dowloadFile(@RequestParam(name = "file") String fileName) throws IOException {
        S3Object s3Object = awsConfig.getClient().getObject(awsConfig.getAwsBucket(), fileName);
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
        awsConfig.getClient().deleteObject(awsConfig.getAwsBucket(), fileName);
        return UploadResponse.builder().message("Arquivo deletado com sucesso!").fileName(fileName).build();
    }

    private String getFormattedFileName(MultipartFile filename) {
        long timeStamp = System.currentTimeMillis();
        String fileName = filename.getOriginalFilename();
        Optional<String> fileExt = getFileExtension(fileName);
        return UUID.randomUUID() + "_" + timeStamp + fileExt.get();
    }

    private Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> "." + f.substring(filename.lastIndexOf(".") + 1));
    }

}
