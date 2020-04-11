package com.yung.auto.framework.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {
        "com.yung.auto.framework.cache",
        "com.yung.auto.framework.data",
        "com.yung.auto.framework.utility",
        "com.yung.auto.framework.demo"})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

//        int port = log.getEnvironment().getProperty("server.port", int.class);
//        System.setProperty("java.awt.headless", "false");
//        Desktop.getDesktop().browse(new URI("http://127.0.0.1:8080/"));
    }


}
