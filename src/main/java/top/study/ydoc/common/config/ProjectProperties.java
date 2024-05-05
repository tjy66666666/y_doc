package top.study.ydoc.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author tjy
 * @date 2024/5/4
 * @apiNote 项目配置的环境变量
 */
@Configuration
public class ProjectProperties {

    /**
     * minio对象存储 存储图片的桶
     */
    @Value("${minio.bucket.img}")
    public String minioBucketOfImg;

}
