package top.study.ydoc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author tjy
 * @date 2024/04/22
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 创建一个CorsConfiguration对象，配置跨域请求的规则
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 允许所有源访问，也可以指定具体的域名
        corsConfiguration.addAllowedHeader("*"); // 允许携带任何头信息
        corsConfiguration.addAllowedMethod("*"); // 允许所有请求方法（GET、POST、PUT等）
        corsConfiguration.setAllowCredentials(true); // 允许携带cookie等认证信息

        // 创建UrlBasedCorsConfigurationSource对象，注册CorsConfiguration配置
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 对所有请求路径生效

        // 返回CorsFilter过滤器对象
        return new CorsFilter(source);
    }
}
