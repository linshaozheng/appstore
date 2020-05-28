package com.linsz.demo.error;

import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


//@Configuration
//public class ErrorPageConfig {
//
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//                container.addErrorPages(error404Page);
//            }
//        };
//    }
//
//}