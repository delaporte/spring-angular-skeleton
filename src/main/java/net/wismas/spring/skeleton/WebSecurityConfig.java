package net.wismas.spring.skeleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.inject.Inject;

/**
 * Created by alexis.delaporte on 15/05/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private Environment environment;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*", "/signin/**", "/signup/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }


    private ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        System.out.println("#########" + registry.registeredProviderIds().size() + "-" + registry.registeredProviderIds());
        registry.addConnectionFactory(new GoogleConnectionFactory(environment.getProperty("google.clientId"), environment.getProperty("google.clientId")));
        System.out.println("#########" + registry.registeredProviderIds().size() + "-" + registry.registeredProviderIds());
        registry.addConnectionFactory(new FacebookConnectionFactory(environment.getProperty("facebook.clientId"), environment.getProperty("facebook.clientId")));
        System.out.println("#########" + registry.registeredProviderIds().size() + "-" + registry.registeredProviderIds());
        registry.addConnectionFactory(new LinkedInConnectionFactory(environment.getProperty("linkedin.clientId"), environment.getProperty("linkedin.clientId")));
        System.out.println("#########" + registry.registeredProviderIds().size() + "-" + registry.registeredProviderIds());
        registry.addConnectionFactory(new TwitterConnectionFactory(environment.getProperty("twitter.clientId"), environment.getProperty("twitter.clientId")));
        System.out.println("#########" + registry.registeredProviderIds().size() + "-" + registry.registeredProviderIds());
        return registry;
    }

    @Bean
    public ProviderSignInController providerSignInController() {
        ConnectionFactoryLocator connectionFactoryLocator = this.connectionFactoryLocator();
        System.out.println("$$$$$$$$$" + connectionFactoryLocator.registeredProviderIds().size() + "-" + connectionFactoryLocator.registeredProviderIds());

        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup);

        return new ProviderSignInController(
                connectionFactoryLocator,
                usersConnectionRepository,
                new FacebookSignInAdapter());
    }
}
