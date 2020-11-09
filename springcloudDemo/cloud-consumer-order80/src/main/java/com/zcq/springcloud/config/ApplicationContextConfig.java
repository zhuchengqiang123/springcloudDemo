package com.zcq.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate
 */


//@Configuration不可以是final类型；
//@Configuration不可以是匿名类；
//嵌套的configuration必须是静态类。
//相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced//负载均衡 使用该注解赋予RestTemplate负载均衡的能力   默认轮询的负载机制 8001 8002 交替出现
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
//applicationContext.xml -->  <bead id="" class="">
//@Configuation等价于<Beans></Beans>
//@Bean等价于<Bean></Bean>
//@ComponentScan等价于<context:component-scan base-package=”com.dxz.demo”/>

