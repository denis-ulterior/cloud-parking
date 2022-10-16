package br.com.ulteriorti.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Component
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("br.com.ulteriorti.parking"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("U-Parking REST-API")
                .description("REST API for parking APP")
                .version("1.0.0")
                .license("")
                .licenseUrl("")
                .build();
    }
}
