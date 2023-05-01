package edu.tcu.cs.superfrogscheduler.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfiguration {

    @Value("/api/v1")
    private String baseUrl;


    // Set athorization for various endpoints.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // It is recommended to secure your application at the API endpoint level.
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/students/**").permitAll()
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/students/**").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/students").permitAll()
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/payment-forms").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl +"/appearances/**").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/performance-reports/**").permitAll()
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/performance-reports").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/users").hasAuthority("ROLE_admin") // Protect the endpoint.
                        //.requestMatchers(HttpMethod.PUT, this.baseUrl + "/users/**").hasAuthority("ROLE_admin") // Protect the endpoint.
                        //.requestMatchers(HttpMethod.DELETE, this.baseUrl + "/users/**").hasAuthority("ROLE_admin") // Protect the endpoint.


                                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll() // Explicitly fallback to antMatcher inside requestMatchers.

                        //.anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable()) // This is for H2 browser console access.
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    public JwtEncoder jwtEncoder() {
//        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
//        JWKSource<SecurityContext> jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));
//        return new NimbusJwtEncoder(jwkSet);
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
//    }

   /*
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

   */

}
