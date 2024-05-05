package top.study.ydoc.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.integration.annotation.IntegrationComponentScan;
import top.study.ydoc.common.util.MinioUtils;


/**
 * @author tjy
 * @date 2024/05/04
 * @description minio配置类 项目初始化时创建minio的bean
 */

@Configuration
@IntegrationComponentScan
@Slf4j
@Order(value = Integer.MIN_VALUE)
public class MinioConfig {
 
  @Value("${minio.endpoint}")
  private String endpoint;
  @Value("${minio.bucketName}")
  private String bucketName;
  @Value("${minio.accessKey}")
  private String accessKey;
  @Value("${minio.secretKey}")
  private String secretKey;
 
  @Bean
  public MinioUtils creatMinioClient() {
    return new MinioUtils(endpoint, bucketName, accessKey, secretKey);
  }
 
}