package net.wismas.spring.skeleton.api.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

/**
 * Created by alexis.delaporte on 15/05/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private SocialConnectionSignup socialConnectionSignup;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private RESTLogoutSuccessHandler lougoutSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/*.js", "/*.html", "/login*", "/signin/**", "/signup/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().formLogin().loginPage("/api/login")
                .and().logout().logoutUrl("/api/logout")
                .and().formLogin().successHandler(authenticationSuccessHandler)
                .and().formLogin().failureHandler(authenticationFailureHandler)
                .and().logout().logoutSuccessHandler(lougoutSuccessHandler)
                .and().logout().permitAll()
                .and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Bean
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) this.usersConnectionRepository)
                .setConnectionSignUp(this.socialConnectionSignup);

        return new ProviderSignInController(
                this.connectionFactoryLocator,
                this.usersConnectionRepository,
                new SocialSignInAdapter());
    }
}
