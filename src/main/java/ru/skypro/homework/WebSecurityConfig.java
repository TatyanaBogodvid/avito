package ru.skypro.homework;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {
  private static final String[] AUTH_WHITELIST = {
          "/swagger-resources/**",
          "/swagger-ui.html",
          "/v3/api-docs",
          "/webjars/**",
          "/login", "/register",
          "/ads", "/ads/*/image",
          "/users", "/users/*/image"
  };

  private final UserService userService;

  public WebSecurityConfig(UserService userService) {
    this.userService = userService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeHttpRequests((authz) ->
                    authz
                            .mvcMatchers(AUTH_WHITELIST).permitAll()
                            .mvcMatchers("/ads/**", "/users/**").authenticated()
            )
            .cors().and().httpBasic(withDefaults());
  }
}
