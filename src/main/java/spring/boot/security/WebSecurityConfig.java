package spring.boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/index").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/products/*").hasRole("ADMIN")
//                .antMatchers("/user").hasRole("USER")
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/login", "/", "/homepage").permitAll()
                .antMatchers("/**/**").hasRole("ADMIN")
//                .antMatchers("/**/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/homepage", false)
                .failureUrl("/login.html?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID");

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
