package com.project.library_app_react_springboot.config;

import com.project.library_app_react_springboot.entity.Book;
import com.project.library_app_react_springboot.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/*
 * The MyDataRestConfig class is used in a Spring Boot application to customize the behavior of Spring Data REST endpoints.
 * Disable specified HTTP methods (POST, DELETE, PUT, PATCH) for both item and collection resources of the Book entity.
 * Expose Entity IDs, Configure CORS (Cross-Origin Resource Sharing): Allow requests from a specific origin (http://localhost:3000),
 * which is typically the frontend application
 *  */

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration, CorsRegistry corsRegistry) {
        // RepositoryRestConfiguration object used to configure Spring Data REST settings.
        // CorsRegistry object used to configure CORS settings
        HttpMethod[] unsupportedAction = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.PATCH};
        configuration.exposeIdsFor(Book.class); // Ensures that IDs of Book entities are exposed
        configuration.exposeIdsFor(Review.class);
        disableHttpMethods(Book.class, configuration, unsupportedAction);
        disableHttpMethods(Review.class, configuration, unsupportedAction);

        // Configure CORS
        //able to make req to frontend

        corsRegistry.addMapping("/**").allowedOrigins("http://localhost:3000");
    }

    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration configuration,
                                    HttpMethod[] unsupportedActions) {

        configuration.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
        // .withItemExposure() and .withCollectionExposure() to disable the specified unsupportedActions for both item (single entity)
        // and collection (list of entities) resources of the domain type (theClass).
    }
}
