package com.opsigte.e.gateway.config;

import com.opsigte.e.gateway.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * <p> @ClassName: <i>WebConfiguration</i></p>
 * <p> @Description: <i>解决全局上下文编码问题</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/7/25 21:03</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {


    /**
     * responseBodyConverter 和configureMessageConverters 两个方法解决中文乱码
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        // 取消返回 AcceptCharset
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        return stringHttpMessageConverter;
    }

    @Bean
    public AuthorizationInterceptor authorizeInterceptor(){
        return new AuthorizationInterceptor();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
        // 这里必须加上加载默认转换器，不然bug玩死人，并且该bug目前在网络上似乎没有解决方案
        // 百度，谷歌，各大论坛等。你可以试试去掉。
        addDefaultHttpMessageConverters(converters);
    }


    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        // 忽略请求后缀 如：todo.json , todo.html ,todo ----> 相应的todo映射方法
        // true--忽略请求后缀，false--匹配请求后缀
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 解决springboot整合swagger2.9.2版本无法访问 swagger-ui.html页面的问题
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor())
                .addPathPatterns("/api/**");
                // 过滤登录接口
                //.excludePathPatterns("/api/user/login")
                //过滤swagger页面
                //.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/doc.html/**");

    }
}
