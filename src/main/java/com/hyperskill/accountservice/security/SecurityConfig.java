package com.hyperskill.accountservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig {
    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private UserDetailsService uds;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("error/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                .requestMatchers(HttpMethod.POST, "api/auth/changepass").authenticated()

                .requestMatchers(HttpMethod.POST,"/api/acc/payment").permitAll()
                .requestMatchers(HttpMethod.PUT,"/api/acc/payment").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/empl/payment").authenticated()

                .requestMatchers(HttpMethod.GET,"/api/admin/user").hasAuthority("ROLE_ADMINISTRATOR")
                .requestMatchers(HttpMethod.DELETE,"/api/admin/user/{username}").hasAuthority("ROLE_ADMINISTRATOR")
                .requestMatchers(HttpMethod.PUT,"/api/admin/user/role").hasAuthority("ROLE_ADMINISTRATOR")
                .requestMatchers(HttpMethod.PUT,"/api/admin/user/access").hasAuthority("ROLE_ADMINISTRATOR")

                .requestMatchers(HttpMethod.GET,"/api/security/events").hasAuthority("ROLE_AUDITOR")

                .anyRequest().denyAll()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .authenticationProvider(authenticationProvider());
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(uds);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }
}
