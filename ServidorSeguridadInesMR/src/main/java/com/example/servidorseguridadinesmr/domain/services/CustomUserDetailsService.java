package com.example.servidorseguridadinesmr.domain.services;


import com.example.servidorseguridadinesmr.data.dao.repositories.CredentialsRepository;
import com.example.servidorseguridadinesmr.data.model.CredentialEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final CredentialsRepository credentialsRepository;

    public CustomUserDetailsService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CredentialEntity userCredential = credentialsRepository.findByUsername(username);

        if (userCredential == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                //.roles(userCredential.getRole())
                .build();

    }
}
