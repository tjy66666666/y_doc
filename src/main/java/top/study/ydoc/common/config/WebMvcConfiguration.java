package top.study.ydoc.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.study.ydoc.common.json.JacksonObjectMapper;
import top.study.ydoc.interceptor.JwtTokenAdminInterceptor;
import top.study.ydoc.interceptor.JwtTokenUserInterceptor;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 *
 * @author tjy
 * @date 2024/05/07
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/employee/login");
        registry.addInterceptor(jwtTokenUserInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login").excludePathPatterns("/user/wx/login");
    }

    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 扩展SpringMVC框架的消息转换器
     *
     * @param converters the list of configured converters to extend
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器,对象转换器可以将Java对象序列化为Json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转换器加入容器中
        converters.add(0, converter);//index:0 表示将自己的转换器放在首位,优先使用
    }
}
