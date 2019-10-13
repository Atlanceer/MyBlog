package atlan.ceer.config;


import atlan.ceer.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/adminUser/**")
                .addPathPatterns("/admin/*.html")
                .excludePathPatterns("/admin/login.html")
                .excludePathPatterns("/adminUser/login")
                .excludePathPatterns("/adminUser/logout");
    }
}
