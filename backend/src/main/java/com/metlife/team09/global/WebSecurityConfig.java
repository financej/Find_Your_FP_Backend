package com.metlife.team09.global;

import com.metlife.team09.global.jwt.JwtAuthenticationFilter;
import com.metlife.team09.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final JwtTokenProvider jwtTokenProvider;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().configurationSource(corsConfigurationSource()); //cors 활성화
            http
                .headers().frameOptions().disable().and()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                    .and()
                    .authorizeRequests()
                    .antMatchers( "/api/auth/**","/h2-console/**").permitAll()
                    .antMatchers("/ws/chat/**", "/chat/**", "/chats/**").permitAll()


                .anyRequest().authenticated();

            http.addFilterAfter(jwtAuthenticationFilter(), LogoutFilter.class);
        }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedOriginPattern("*"); // 배포 전 모두 허용
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
            return new JwtAuthenticationFilter(jwtTokenProvider);
    }
}