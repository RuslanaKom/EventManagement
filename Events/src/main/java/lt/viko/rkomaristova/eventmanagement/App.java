package lt.viko.rkomaristova.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
public class App extends SpringBootServletInitializer {@Bean
		public Docket swaggerDocket() {
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.basePackage("lt.viko.rkomaristova.eventmanagement"))
					.build();
		}
			private ApiInfo apiInfo() {
				return new ApiInfoBuilder()
						.title("REST Documentation")
						.version("0.0.1-SNAPSHOT")
						.build();
			}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
