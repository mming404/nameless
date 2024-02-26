package com.ysm.common.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinoConfig {
    @Value("${minio.bucket1.endpoint}")
    private String endpoint;
 
    @Value("${minio.bucket1.port}")
    private int port;
 
    @Value("${minio.bucket1.accessKey}")
    private String accessKey;
 
    @Value("${minio.bucket1.secretKey}")
    private String secretKey;
 
    @Value("${minio.bucket1.bucket}")
    private String bucket;
 
    @Value("${minio.bucket1.publicEndpoint}")
    private String publicEndpoint;
 
 
    @Bean(name = "MinioClient")
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint,port,false)
                .credentials(accessKey, secretKey)
                .build();
    }
}