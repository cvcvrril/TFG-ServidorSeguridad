package com.example.servidorseguridadinesmr.common;

import com.example.servidorseguridadinesmr.data.dao.DaoCredential;
import com.example.servidorseguridadinesmr.data.dao.DaoUser;
import com.example.servidorseguridadinesmr.data.dao.connection.JPAUtil;
import com.example.servidorseguridadinesmr.data.dao.impl.DaoCredentialImpl;
import com.example.servidorseguridadinesmr.data.dao.impl.DaoUserImpl;
import com.example.servidorseguridadinesmr.data.dao.repositories.CredentialsRepository;
import com.example.servidorseguridadinesmr.domain.services.CustomUserDetailsService;
import com.example.servidorseguridadinesmr.utils.Constantes;
import com.example.servidorseguridadinesmr.utils.RandomBytesGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.mail.password}")
    private String fromPassword;
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private int mailPort;

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

    @Bean
    public TemplateEngine templateEngine(){return new TemplateEngine();}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(fromEmail);
        mailSender.setPassword(fromPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put(Constantes.MAIL_TRANSPORT_PROTOCOL, Constantes.SMTP);
        props.put(Constantes.MAIL_SMTP_AUTH, Constantes.TRUE);
        props.put(Constantes.MAIL_SMTP_STARTTLS_ENABLE, Constantes.TRUE);
        props.put(Constantes.MAIL_DEBUG, Constantes.TRUE);

        return mailSender;
    }


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

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RandomBytesGenerator randomBytesGenerator(){
        return new RandomBytesGenerator();
    }

}
