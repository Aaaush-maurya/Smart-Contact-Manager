package com.scm.SCM20.config;

import com.scm.SCM20.services.impl.SecurityCustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService(){
//
////        UserDetails user1 = User
////                .withDefaultPasswordEncoder()
////                .username("Admin")
////                .password("12345")
////                .build();
////
////        var  inMemoryUserDetailsManager= new InMemoryUserDetailsManager(user1);
////        return inMemoryUserDetailsManager;
////    }
//
//}
    @Autowired
    private SecurityCustomUserService userDetailsService;
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return  new BCryptPasswordEncoder( 10);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize->{
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();

        });

        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login");
             formLogin.loginProcessingUrl("/authenticate")
                     .successForwardUrl("/user/dashboard");
//                    formLogin.defaultSuccessUrl("/user/dashboard" ,true);
//                    formLogin.failureForwardUrl("/login?error=true");
                    formLogin.passwordParameter("password");
                    formLogin.usernameParameter("email");
        });

        httpSecurity.csrf(AbstractHttpConfigurer ::disable);

        httpSecurity.logout(logout ->
                  logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login"));




                return httpSecurity.build();

    }


}