package com.wild.corp.service;

import com.wild.corp.model.ConfirmationToken;
import com.wild.corp.model.User;
import com.wild.corp.repositories.ConfirmationTokenRepository;
import com.wild.corp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    void saveConfirmationToken(ConfirmationToken confirmationToken) {

        confirmationTokenRepository.save(confirmationToken);
    }


    void deleteConfirmationToken(Long id){

        confirmationTokenRepository.deleteById(id);
    }



}