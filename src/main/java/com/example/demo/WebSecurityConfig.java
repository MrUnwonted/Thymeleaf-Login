package com.example.demo;


import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager; 


 
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetailsManager  userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("arjun@gmail.com")
                .password(passwordEncoder().encode("123"))
                .authorities("read").build();
        userDetailsManager.createUser(user);
        return userDetailsManager;

    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//  	  http.authorizeRequests().antMatchers("/").permitAll();

        http.authorizeRequests().antMatchers("/**")
                .permitAll()
                .and()
//                .rememberMe()

//                .and()
                .formLogin(form -> form
                        .defaultSuccessUrl("/")
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                )
                .rememberMe()
                .key("AbcdEfGhIjKlMnOPqRs_1234567890").tokenValiditySeconds(7 * 24 * 60 * 60)
                .and()
                .logout().permitAll()
        ;


//                .key("AbcdEfGhIjKlMnOPqRs_1234567890").tokenValiditySeconds(7 * 24 * 60 * 60)


    }


//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("arjun@gmail.com")
//                .password("123");
//               .authorities("USER");
//    }

}


//http.csrf().disable().authorizeRequests().anyRequest()
//        .authenticated().and()
//        .formLogin()
//        .loginPage("/login")
//        .usernameParameter("email")
//        .defaultSuccessUrl("/")
//        .permitAll()
//        .and().logout().permitAll()
//        .and()
//        .rememberMe()
//        .key("AbcdEfGhIjKlMnOPqRs_1234567890").tokenValiditySeconds(7 * 24 * 60 * 60)
//        ;
