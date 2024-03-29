package com.paulocezar.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.paulocezar.serialization.converter.YamlJackson2HttpMessageConverter;

//@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void extendMessageConverters (List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
		
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET","POST","PUT","DELETE","OPTIONS", "HEAD", "TRACE", "CONNECT");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
// Via Extension: localhost:8080/person.x-yml
//		configurer.favorParameter(false)
//		          .ignoreAcceptHeader(false)
//		          .defaultContentType(MediaType.APPLICATION_JSON)
//				  .mediaType("json", MediaType.APPLICATION_JSON);
		
		/* Via Query Param http://localhost:8080/api/person/v1?mediaType=json
		 * configurer.favorPathExtension(false) .favorParameter(true)
		 * .parameterName("mediaType") .ignoreAcceptHeader(true)
		 * .useRegisteredExtensionsOnly(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON);
		 */
	
		// Pelo cabeçalho (header)
		
		configurer.favorPathExtension(false)
		.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("x-yaml", MEDIA_TYPE_YML);

	}
}