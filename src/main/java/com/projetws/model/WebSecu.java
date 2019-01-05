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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 *
 * @author
 */
@Configuration
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
        	.antMatchers("/updateCountry").hasRole("ADMIN")
        	.antMatchers("/updateDepartment").hasRole("ADMIN")
        	.antMatchers("/updateJobHistory").hasRole("ADMIN")
        	.antMatchers("/updateLocation").hasRole("ADMIN")
        	.antMatchers("/updateRegion").hasRole("ADMIN")
        	.antMatchers("/updateEmployee").hasRole("EDITOR")
        	.antMatchers("/updateJob").hasRole("EDITOR")
        	
            .antMatchers("/location/all").hasRole("CONSULT")
            .antMatchers("/region/all").hasRole("CONSULT")
            .antMatchers("/department/all").hasRole("EDITOR")
            .antMatchers("/employee/all").hasRole("EDITOR")
            .antMatchers("/job/all").hasRole("EDITOR")
            .antMatchers("/jobHistory/all").hasRole("EDITOR")
            .antMatchers("/country/all").hasRole("EDITOR")
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/appLogin")
            .usernameParameter("app_username")
            .passwordParameter("app_password")
            .defaultSuccessUrl("/all")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/all")
            .permitAll();
    }

    @Autowired
    EmployeeService userDetailsService;

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {

        auth.inMemoryAuthentication()
        .withUser("editor").password("editor").roles("CONSULT", "EDITOR")
        .and().withUser("consult").password("consult").roles("CONSULT")
        .and().withUser("admin").password("admin").roles("CONSULT", "EDITOR", "ADMIN");
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(userDetailsService.encoder);
    }
}
