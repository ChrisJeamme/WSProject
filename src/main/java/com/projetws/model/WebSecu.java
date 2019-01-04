/*
 *      _____   _                   _            _____                      _   
 *     |  __ \ (_) Website Project | |          / ____|                    | |  
 *     | |  | | _  _ __  ___   ___ | |_    ___ | (___   _ __    ___   _ __ | |_ 
 *     | |  | || || '__|/ _ \ / __|| __|  / _ \ \___ \ | '_ \  / _ \ | '__|| __|
 *     | |__| || || |  |  __/| (__ | |_  |  __/ ____) || |_) || (_) || |   | |_ 
 *     |_____/ |_||_|   \___| \___| \__|  \___||_____/ | .__/  \___/ |_|    \__|
 *                                                     | |                                                                                              
 *  ----Authors----                                    |_| 
 * Dimitri BRUYERE
 * Clément COLIN
 * Christopher JEAMME
 *  ---------------
 */
package com.projetws.model;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author
 */
@Configuration
@EnableWebSecurity
public class WebSecu extends WebSecurityConfigurerAdapter
{

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http.authorizeRequests()
            .antMatchers("/admin").hasAnyAuthority("ADMIN")
            .antMatchers("/articleCreation").hasAnyAuthority("EDITOR")
            .and()
            .formLogin()
            .loginPage("/signin")
            .loginProcessingUrl("/appLogin")
            .usernameParameter("app_username")
            .passwordParameter("app_password")
            .defaultSuccessUrl("/home")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/home")
            .permitAll();
    }

    @Autowired
    UserService userDetailsService;

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {

        /*auth.inMemoryAuthentication()
        .withUser("robert").password("toto").roles("USER", "ADMIN")
        .and().withUser("bob").password("toto").roles("USER")*/
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(userDetailsService.encoder);
    }
}
