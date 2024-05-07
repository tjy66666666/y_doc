package top.study.ydoc.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.study.ydoc.common.config.ProjectProperties;
import top.study.ydoc.common.util.MinioUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author tjy
 * @date 2024/5/4
 * @description 项目初始化代码
 */
@Component
@Slf4j
@Order(0)
public class Init {

    @Resource
    private ProjectProperties properties;


    @PostConstruct
    public void init() {

        // 1.初始化minio桶
        //minioInit();

        // 2.加载项目静态资源放到缓存中防止刷资源
        //staticResourcesInit();

    }


    private void minioInit() {
        log.info("minio 初始化...");

        try {
            MinioUtils.createBucket(properties.minioBucketOfImg);
        } catch (Exception e) {
            log.warn("minio初始化异常：{}", e.getMessage());
        }
        log.info("minio 初始化完成");
    }

    private void staticResourcesInit() {
        log.info("初始化项目静态资源... ");
        // TODO 初始化项目静态资源 放到缓存中

        log.info("初始化项目静态资源完成");
    }

}
