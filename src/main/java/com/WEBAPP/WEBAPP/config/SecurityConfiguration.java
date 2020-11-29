package com.WEBAPP.WEBAPP.config;


import com.WEBAPP.WEBAPP.handlers.CustomAccessDeniedHandler;
import com.WEBAPP.WEBAPP.service.MyUserDetailsService;
import com.WEBAPP.WEBAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final MyUserDetailsService service;

    @Autowired
    public SecurityConfiguration(MyUserDetailsService service){
        this.service=service;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());}

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(service);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable().authorizeRequests()
                .antMatchers(
                "/","/list**", "/showDescription/**","/description/**","/index",
                        "/showComment/**",
                "/registration/**",
                "/assets/**",
                "/js/**",
                "/css/**",
                        "/showNewContactForm", "/saveContact",
                        "/eventAdress/**","/eventAdress*","/showAdressForEvent/**",
                "/showTimetableForm/**", "/showTimetableForm/**",
                "/img/**").permitAll()

                .antMatchers("/new_event", "/showNewEventForm").hasAnyAuthority("ROLE_ADMIN", "ROLE_CREATOR")
                .antMatchers("/creatorProm","promoteUser").hasAnyAuthority("ROLE_ADMIN")

                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();


    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web resources
        web.ignoring().antMatchers("/assets/**", "/css/**","/scripts/**","/images/**");
    }

}
