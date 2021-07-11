package com.ms.rest.conf.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] excludeUrls = {

    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers( "/v2/api-docs",
                "/webjars/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/actuator/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .anonymous().and().authorizeRequests()
                .antMatchers(excludeUrls).permitAll()
//                .antMatchers("/api/**").hasAuthority(OperatorRoleEnum.USER.name())
//                .antMatchers("/admin-api/**").hasAuthority(OperatorRoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and().addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);

    }

}
