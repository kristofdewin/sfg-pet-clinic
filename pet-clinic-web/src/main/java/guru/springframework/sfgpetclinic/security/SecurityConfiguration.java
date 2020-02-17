package guru.springframework.sfgpetclinic.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//turns on spring security
@EnableWebSecurity
//tells spring boot to use this class for configuring security
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //define URL's
    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //disable cross-site request forgery protection, build in in vaadin
        http.csrf().disable()

                // Uses CustomRequestCache to track unauthorized requests so that users are redirected,
                // appropriately after login.
                .requestCache().requestCache(new CustomRequestCache())

                //turn on authorization
                .and().authorizeRequests()

                //allow all internal traffic from vaadin framework
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll() //

                //allows all authenticated traffic
                .anyRequest().authenticated()

                //Enables form-based login and permits unauthenticated access to it
                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()

                //configure the login page URL's
                .loginProcessingUrl(LOGIN_PROCESSING_URL) //
                .failureUrl(LOGIN_FAILURE_URL)

                //configure the logout URL
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL); //
    }

    @Override
    //Defines a single user with the username "user" and password "password",
    // in an in-memory DetailsManager.
    protected UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }


    //exclude Vaadin-framework communication and static assets from Spring Security.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                //vaadin static resources
                "/VAADIN/**",
                //standard favicon URI
                "/favicon.ico",
                //the robots exclusion standard
                "/robots.txt",
                //web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                //icons and images
                "/icons/**",
                "/images/**",
                "/styles/**",
                //static resources (development)
                "/frontend/**",
                //h2-debugging console (development)
                "/h2-console/**",
                //static resources(production)
                "/frontend-es5/**",
                "/frontend-es6/**");
    }

}
