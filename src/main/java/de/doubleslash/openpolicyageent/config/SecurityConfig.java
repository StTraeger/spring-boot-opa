package de.doubleslash.openpolicyageent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService users()  {
        final User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        final UserDetails tegernseerUser = userBuilder
                .username("tegernseer")
                .password("test1234")
                .roles("TEGERNSEER")
                .build();
        final UserDetails augustinerUser = userBuilder
                .username("augustiner")
                .password("test1234")
                .roles("AUGUSTINER")
                .build();
        return new InMemoryUserDetailsManager(tegernseerUser, augustinerUser);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity security) throws Exception {

        security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/beers/tegernseer").hasRole("TEGERNSEER")
                        .requestMatchers("/beers/augustiner").hasRole("AUGUSTINER")
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/opa/**").permitAll()
                        .anyRequest().authenticated()
        );

        return security.build();
    }



}
