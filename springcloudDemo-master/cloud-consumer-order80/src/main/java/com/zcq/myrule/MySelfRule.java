package com.zcq.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon的规则，
 * 该类不能被 @SpringBootApplication 注解里的@CompomentScan 注解扫描到，否则所有的RibbonClient都去共享这个配置
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule muRule(){
        return new RandomRule();//定义为随机
    }
}
