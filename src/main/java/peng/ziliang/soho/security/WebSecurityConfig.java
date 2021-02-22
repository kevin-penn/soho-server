package peng.ziliang.soho.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import peng.ziliang.soho.common.constants.URLPrefix;
import peng.ziliang.soho.repository.UserRepo;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepo userRepo;
    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfig(UserRepo userRepo, JwtTokenFilter jwtTokenFilter) {
        this.userRepo = userRepo;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        http.authorizeRequests()
                // Our public endpoints
                .antMatchers(URLPrefix.API_PUBLIC + "/**").permitAll()
                // Our private endpoints
                .anyRequest().authenticated();

        // Add JWT token filter
//        http.addFilterBefore(
//                jwtTokenFilter,
//                UsernamePasswordAuthenticationFilter.class
//        );

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> userRepo
                .findUserByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", email)
                        )
                ));
    }

    // Used by spring security if CORS is enabled.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
