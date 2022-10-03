package com.cos.jwt.config;

import com.cos.jwt.config.filter.MyFilterA;
import com.cos.jwt.config.filter.MyFilterB;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 필터체인에 있는 필터가 먼저 동작된다.
// 따라서 커스텀 필터를 스프링 필터체인의 필터들보다 먼저 동작시키려면 필터체인 구조를 확인하고 맨 위의 필터 After 로 추가하면 된다.
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilterA> filterA() {
        FilterRegistrationBean<MyFilterA> bean = new FilterRegistrationBean<>(new MyFilterA());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호가 필터 중 가장 먼저 실행됨.

        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilterB> filterB() {
        FilterRegistrationBean<MyFilterB> bean = new FilterRegistrationBean<>(new MyFilterB());
        bean.addUrlPatterns("/*");
        bean.setOrder(1); // 낮은 번호가 필터 중 가장 먼저 실행됨.

        return bean;
    }
}
