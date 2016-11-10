package com.zero.config;

import com.zero.business.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by Jin_ on 2016/11/10.
 */
@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
@Order(6)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Bean
  public UserService getUserService() {
    return new UserService();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userService);
  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/oauth/token")
        .permitAll()
        .and().authorizeRequests()
        .antMatchers("/api/**")
        .authenticated()
        .anyRequest()
        .permitAll()
        .and().exceptionHandling();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    //web.ignoring().antMatchers("/api/users/**");
  }

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.antMatcher("/api/**").authorizeRequests().anyRequest().authenticated();
    }
  }
}
