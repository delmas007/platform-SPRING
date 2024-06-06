package com.example.backplatforme.Config;


import com.example.backplatforme.Service.Imp.UserDetailServiceImp;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    UserDetailServiceImp userDetailServiceImp;
    PasswordEncoder passwordEncoder;
    RsakeysConfig rsakeysConfig;

    @Bean
    AuthenticationManager authenticationManager(@Lazy UserDetailsService userDetailsService){
        DaoAuthenticationProvider authProvier = new DaoAuthenticationProvider();
        authProvier.setPasswordEncoder(passwordEncoder);
        authProvier.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvier);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/connexion/**","/tuto/**", "/tutos/**","/inscription/**").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()))
                .cors(Customizer.withDefaults())
                .userDetailsService(userDetailServiceImp);
        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
    }
    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk= new RSAKey.Builder(rsakeysConfig.publicKey()).privateKey(rsakeysConfig.privateKey()).build();
        JWKSource<SecurityContext> jwkSource= new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }
}
