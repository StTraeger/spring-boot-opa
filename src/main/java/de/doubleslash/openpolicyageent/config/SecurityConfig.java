package de.doubleslash.openpolicyageent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService users()  {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        UserDetails tegernseerUser = userBuilder
                .username("tegernseer")
                .password("test1234")
                .roles("TEGERNSEER")
                .build();
        UserDetails augustinerUser = userBuilder
                .username("augustiner")
                .password("test1234")
                .roles("AUGUSTINER")
                .build();
        return new InMemoryUserDetailsManager(tegernseerUser, augustinerUser);
    }



}
