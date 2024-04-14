package com.cx.gmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xcx
 * @date 2024-04-10 21:15
 */
//开启了spring的组件扫描和springboot的自动配置
@SpringBootApplication
//指定扫描路径，将路径下带有注解的类自动装配到bean容器中
@ComponentScan({"com.cx.gmall"})
//从注册中心（zookeeper等）获取注册信息及提供注册服务
@EnableDiscoveryClient
public class ServiceProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class,args);
    }
}
