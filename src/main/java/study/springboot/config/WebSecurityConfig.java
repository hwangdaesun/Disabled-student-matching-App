package study.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security의 웹 보안 지원을 활서화하고, SpringMVC를 제공한다.
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //SecurityFilterChain 빈은 보안되어야 하는 URL 경로와 보안되지 않아야 하는 URL 경로를 정의합니다.
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/home").permitAll() // 인증이 필요하지 않음
                        .anyRequest().authenticated() // 다른데는 인증이 필요함
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll() //
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
