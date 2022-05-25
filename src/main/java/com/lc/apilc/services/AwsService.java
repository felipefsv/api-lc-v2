package com.lc.apilc.services;

import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.lc.apilc.configuration.AWSConfig;
import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.LcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AwsService {

    @Autowired
    private AWSConfig awsConfig;

    public String uploadFile(MultipartFile file, String acl) throws IOException {
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
            throw new LcException("ACL Inválido!", ErrorCodes.ACL_INVALIDO);
        }

        PutObjectResult uploadResult = awsConfig.getClient().putObject(new PutObjectRequest(awsConfig.getAwsBucket(), fileName, modifiedFile)
                .withCannedAcl(cannedAccessControlList));

        modifiedFile.delete();

        return fileName;
    }

    public byte[] dowloadFile(String fileName) throws IOException {
        S3Object s3Object = awsConfig.getClient().getObject(awsConfig.getAwsBucket(), fileName);
        S3ObjectInputStream objectContent = s3Object.getObjectContent();
        return IOUtils.toByteArray(objectContent);
    }


    public void deleteFile(String fileName) {
        awsConfig.getClient().deleteObject(awsConfig.getAwsBucket(), fileName);
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
