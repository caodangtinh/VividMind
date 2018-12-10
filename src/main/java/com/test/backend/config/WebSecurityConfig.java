package com.test.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private AuthenticationEntryPoint authEntryPoint;
    
    @Autowired
    private UserDetailsService userDetailsService;
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
 
        // All requests send to the Web Server request must be authenticated
        //http.authorizeRequests().anyRequest().authenticated();
 
        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
        
        
        http
        .authorizeRequests()
        .antMatchers("/api/v1/tours/refresh").hasRole("ADMIN");
        
        http
        .authorizeRequests()
        .antMatchers("/api/v1/tours").hasRole("USER");
        
        http.anonymous().disable();
    }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
         
//        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
//        mngConfig = auth.inMemoryAuthentication();
//        
//        System.out.println(this.passwordEncoder().encode("admin12"));
// 
//        UserDetails u1 = User.withUsername("admin").password(this.passwordEncoder().encode("admin12")).roles("ADMIN").build();
//        UserDetails u2 = User.withUsername("john").password(this.passwordEncoder().encode("john12")).roles("USER").build();
// 
//        mngConfig.withUser(u1);
//        mngConfig.withUser(u2);
    }
 
}
