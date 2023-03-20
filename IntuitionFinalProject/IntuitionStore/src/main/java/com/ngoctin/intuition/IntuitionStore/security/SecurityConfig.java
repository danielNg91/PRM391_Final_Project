package com.ngoctin.intuition.IntuitionStore.security;
import com.ngoctin.intuition.IntuitionStore.filter.MyAuthentication;
import com.ngoctin.intuition.IntuitionStore.filter.MyAuthorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@EnableWebSecurity
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        log.info("CorsConfigurationSource : Ngoc Tin on da mic !");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        MyAuthentication myAuthentication = new MyAuthentication(authenticationManagerBean());
        MyAuthorization myAuthorization = new MyAuthorization();
        myAuthentication.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.cors().and().sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/","/api/register/**","/api/login/**").permitAll();
        // De su dung cai api nay` voi phuong thuc GET thi phai co quyen ADMIN
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/category/searchByLikeName/{name}").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/products/searchByLikeName/{name}").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/product/getAllProducts").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user/getAuthenticatedUser").hasAnyAuthority("ADMIN","USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "api/promotion/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "api/promotion/**/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "api/promotion/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "api/promotion/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "api/order/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "api/order/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "api/order/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "api/order/**").hasAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(myAuthentication);
        http.addFilterBefore(myAuthorization, UsernamePasswordAuthenticationFilter.class);
    }
}
