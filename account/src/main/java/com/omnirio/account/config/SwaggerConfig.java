
package com.omnirio.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;

@EnableAutoConfiguration
public class SwaggerConfig {

	@Value("${swagger.title}")
	private String swaggerTitle;

	@Value("${swagger.description}")
	private String swaggerDescription;

	@Value("${swagger.version}")
	private String swaggerVersion;

	@Value("${swagger.packageBase}")
	private String packageBase;

	@Value("${swagger.contextPath}")
	private String contextPath;

	@Value("${swagger.copyright.by}")
	private String copyRightBy;
	
	@Value("${swagger.website}")
	private String website;
	
	@Value("${swagger.email}")
	private String email;
	
	/*********************************************************
	 * Initialise Swagger setup
	 *********************************************************/
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(packageBase))
				.paths(PathSelectors.regex("/.*")).build()
				.apiInfo(new ApiInfoBuilder().title(swaggerTitle).description(swaggerDescription)
						.contact(new Contact(copyRightBy, website, email))
						.version(swaggerVersion).build())
				.pathProvider(new RelativePathProvider(null) {
					@Override
					public String getApplicationBasePath() {
						return contextPath;
					}
				});
	}

}
