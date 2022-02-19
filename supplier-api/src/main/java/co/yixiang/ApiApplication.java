/**
 * Copyright (C) 2018-2021
 * All rights reserved, Designed By www.yixiang.co
 * 注意：本软件为www.yixiang.co开发研制
 */
package co.yixiang;

import co.yixiang.app.filter.ApiFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author hupeng <610796224@qq.com>
 */
@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(basePackages = {"co.yixiang.app.common.persistence.dao","co.yixiang.app.modular.*.dao"})
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ==========   ლ(´ڡ`ლ)ﾞ  \n" );
    }
    @Bean
    public FilterRegistrationBean<ApiFilter> registration() {
        //创建filter
        ApiFilter accesLogFilter = new ApiFilter();
        //注册过滤器
        FilterRegistrationBean<ApiFilter> registration = new FilterRegistrationBean<>(accesLogFilter);
        //添加条件
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
