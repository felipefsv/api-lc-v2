package com.lc.apilc.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AWSConfig {

    @Value("${aws.key}")
    private String awsKey;

    @Value("${aws.secret}")
    private String awsSecret;

    @Value("${aws.bucket}")
    private String awsBucket;

    @Bean(name = "awsBucket")
    public String awsBucketName() {
        return this.awsBucket;
    }

    @Bean
    public AmazonS3 getClient() {
        AWSCredentials credential = new BasicAWSCredentials(awsKey, awsSecret);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credential))
                .withRegion(Regions.SA_EAST_1).build();
    }
}
