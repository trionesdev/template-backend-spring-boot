package com.ms.rest.conf.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] excludeUrls = {
            "/**"
    };

    private final String[] excludeGetUrls = {
            "/**"
    };

    private final String[] excludePostUrls = {
            "/**"
    };

    private final String[] excludePutUrls = {
            "/**"
    };

    private final String[] excludeDeleteUrls = {
            "/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/v2/api-docs",
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
                .antMatchers(HttpMethod.GET, excludeGetUrls).permitAll()
                .antMatchers(HttpMethod.POST, excludePostUrls).permitAll()
                .antMatchers(HttpMethod.PUT, excludePutUrls).permitAll()
                .antMatchers(HttpMethod.DELETE, excludeDeleteUrls).permitAll()
//                .antMatchers("/api/**").hasAuthority(OperatorRoleEnum.USER.name())
//                .antMatchers("/admin-api/**").hasAuthority(OperatorRoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and().addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);

    }

}
