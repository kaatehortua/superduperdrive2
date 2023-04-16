package com.udacity.jwdnd.course1.cloudstorage.security;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authBuild){
        authBuild.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/signup","/css/**","/js/**","/h2-console/**").permitAll()
                .anyRequest().authenticated().and().csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().disable();
        http.formLogin().loginPage("/login").permitAll();
        http.formLogin().defaultSuccessUrl("/home",true);
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");



    }

}
