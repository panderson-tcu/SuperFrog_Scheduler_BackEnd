package edu.tcu.cs.superfrogscheduler.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@Configuration
public class SecurityConfiguration {


    private final RSAPublicKey publicKey;

    private final RSAPrivateKey privateKey;

//    @Value("${api.endpoint.base-url}")
    @Value("/api/v1")
    private String baseUrl;

    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    private final CustomBearerTokenAuthenticationEntryPoint customBearerTokenAuthenticationEntryPoint;

    private final CustomBearerTokenAccessDeniedHandler customBearerTokenAccessDeniedHandler;

    public SecurityConfiguration(CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint, CustomBearerTokenAuthenticationEntryPoint customBearerTokenAuthenticationEntryPoint, CustomBearerTokenAccessDeniedHandler customBearerTokenAccessDeniedHandler) throws NoSuchAlgorithmException {
        this.customBasicAuthenticationEntryPoint = customBasicAuthenticationEntryPoint;
        this.customBearerTokenAuthenticationEntryPoint = customBearerTokenAuthenticationEntryPoint;
        this.customBearerTokenAccessDeniedHandler = customBearerTokenAccessDeniedHandler;

        // Generate a public/private key pair.
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // The generated key will have a size of 2048 bits.
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    // Set athorization for various endpoints.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        //UC 1: Customer requests an appearance
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/appearances/customer").permitAll()
                        //UC 2, 3: Customer edits/ cancel an appearance
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/appearances/customer/**").permitAll()
                        //UC 4: Spirit director approves/ reject an appearance
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/appearances/admin/**").hasAuthority("ROLE_admin")
                        //UC 5: Spirit director requests a SuperFrog for TCU events
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/appearances/admin").hasAuthority("ROLE_admin")
                        //UC 6: Spirit director/ SuperFrog Student finds appearance requests
                        .requestMatchers(HttpMethod.POST, this.baseUrl +"/appearances/admin/**").hasAuthority("ROLE_admin")
                        //UC 7: Spirit director/ SuperFrog student views an appearance request
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/appearances/admin/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/appearances/**").permitAll()

                        //UC 15: Spirit Director finds SuperFrog student
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/students/search_students").permitAll()
                        //UC 16: Spirit Director views a SuperFrog Student account
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/students/**" ).permitAll()
                        //UC 18: Spirit Director generates TCU Honorarium
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/payment-forms").permitAll()
                        //UC 19: Spirit Director generates a SuperFrog Students performance report
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/performance-reports").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl +"/performance-reports/export/pdf").permitAll()

                        //for logging in
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/users/login").permitAll()




                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/appearances").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/users").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/users").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/users/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/users/**").hasAuthority("ROLE_admin") // Protect the endpoint.
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll() // Explicitly fallback to antMatcher inside requestMatchers.

                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable()) // This is for H2 browser console access.
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                //.httpBasic(Customizer.withDefaults())
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.customBasicAuthenticationEntryPoint))
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt().and()
                        .authenticationEntryPoint(this.customBearerTokenAuthenticationEntryPoint)
                        .accessDeniedHandler(this.customBearerTokenAccessDeniedHandler))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

}
