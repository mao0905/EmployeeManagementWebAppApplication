package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final EmployeeService employeeService;

    @Autowired
    public WebSecurityConfig(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home")
                .permitAll()
                .anyRequest()
                .authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")			// ログイン時のURLの指定
                .defaultSuccessUrl("/hello")	// 認証成功後にリダイレクトする場所の指定
                .permitAll()					// アクセス権限の有無（permitAllは全てのユーザーがアクセス可能)
            )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                Employee employee = employeeService.findByEmployeeId(username);

                if (employee != null) {
                    return User
                    		.withUsername(employee.getEmployeeId())
                            .password(employee.getPass())
                            .roles("USER")
                            .build();
                } else {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
