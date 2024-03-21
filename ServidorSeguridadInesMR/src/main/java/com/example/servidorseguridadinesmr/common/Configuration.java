package com.example.servidorseguridadinesmr.common;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.dao.impl.DaoCredentialImpl;
import com.example.servidorseguridadinesmr.data.dao.impl.DaoUserImpl;
import com.example.servidorseguridadinesmr.data.dao.repositories.CredentialsRepository;
import com.example.servidorseguridadinesmr.domain.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public JPAUtil jpaUtil(){
        return new JPAUtil();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoCredential daoCredential(){
        return new DaoCredentialImpl(jpaUtil());
    }

    @Bean
    public DaoUser daoUser(){
        return new DaoUserImpl(jpaUtil());
    }

    //@Bean
    //public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //    return config.getAuthenticationManager();
    //}

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder encoder) {
        var dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(encoder);
        return dao;
    }

    @Bean
    public UserDetailsService userDetailsService(CredentialsRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

}
