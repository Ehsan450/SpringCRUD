package io.github.hossensyedriadh.springcrud.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().authorizeRequests().antMatchers("/**/*.*", "/webjars/**", "/resources/**", "/login/**", "/actuator/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login").loginProcessingUrl("/authenticate").
                failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/products/", true)
                .and().logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).clearAuthentication(true).logoutSuccessUrl("/login?logout")
                .and().userDetailsService(this.userDetailsService);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withUsername("manoniyospeaker")
                .password("$2a$10$7MDsCr8fbrxjNTNZyIQrT.IXoV.7lujxQc/ef/geUVfVmulikgPFe")
                .roles("USER")
                .build();

        UserDetails adminInfo = User.withUsername("modkhamu")
                .password("$2a$10$EOtoRYngpcUQc4XFeeY0iOj1FpLrs7Fd/xmNF.Gv5aaly1Wn8DaAW")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails, adminInfo);
    }*/
}
