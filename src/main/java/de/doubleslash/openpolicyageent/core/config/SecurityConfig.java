package de.doubleslash.openpolicyageent.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import de.doubleslash.openpolicyageent.core.opa.OpaAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OpaAuthorizationManager authorizationManager;

    public SecurityConfig(final OpaAuthorizationManager opaAuthorizationManager) {
        this.authorizationManager = opaAuthorizationManager;
    }

    @Bean
    public UserDetailsService users() {
        final User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        final UserDetails adminUser = userBuilder
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
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
        final UserDetails floetzingerUser = userBuilder
                .username("floetzinger")
                .password("test1234")
                .roles("FLOETZINGER")
                .build();
        return new InMemoryUserDetailsManager(tegernseerUser, augustinerUser, floetzingerUser, adminUser);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity security) throws Exception {

        security
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/opa/**").permitAll()
                                .requestMatchers("/breweries/**").access(authorizationManager)
                                .anyRequest().authenticated()
                );

        return security.build();
    }

}
