package member;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Intercepter())
                .addPathPatterns("/**") // 인터셉터를 적용할 URL 패턴 설정
                .excludePathPatterns("default/**");    // 인터셉터를 제외할 URL 패턴 설정
    }
}
