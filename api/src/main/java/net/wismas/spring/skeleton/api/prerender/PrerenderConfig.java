package net.wismas.spring.skeleton.api.prerender;

import com.github.greengerong.PreRenderSEOFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by conta on 18/05/2017.
 */
@Configuration
public class PrerenderConfig {

    @Autowired
    private Environment environment;

    @Bean
    public FilterRegistrationBean prerenderRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(prerender());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("prerenderToken", environment.getProperty("prerender.token"));
        registration.setName("prerender");
        registration.setOrder(1);
        return registration;
    }


    public PreRenderSEOFilter prerender(){
        return new PreRenderSEOFilter();
    }
}
