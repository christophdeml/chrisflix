package deml.chrisflix.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${movies.directory}")
    private String moviesDirectory;
    @Value("${movies.images.directory}")
    private String imagesDirectory;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/video/**").addResourceLocations("file:" + moviesDirectory);
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/", "file:" + imagesDirectory).setCacheControl(CacheControl.maxAge(7, TimeUnit.HOURS));
        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/").setCacheControl(CacheControl.maxAge(7, TimeUnit.HOURS));
    }
}
