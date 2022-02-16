package com.example.springbootopa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        final List<AccessDecisionVoter<?>> decisionVoters = List.of(
                new OPAVoter("http://localhost:8181/v1/data/http/authz/allow"));
        return new UnanimousBased(decisionVoters);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/demo/secured").authenticated()
                .accessDecisionManager(accessDecisionManager());
    }
}
