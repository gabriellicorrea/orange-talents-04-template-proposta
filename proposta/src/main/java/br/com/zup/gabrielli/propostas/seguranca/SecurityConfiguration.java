package br.com.zup.gabrielli.propostas.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
        	authorizeRequests               
                .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.GET, "/biometria/**").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.POST, "/bloquearCartao/**").hasAuthority("SCOPE_propostas") 
                .antMatchers(HttpMethod.POST, "/viagem").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
