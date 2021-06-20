package com.wild.corp.controller;

import com.wild.corp.jwt.*;
import com.wild.corp.model.User;
import com.wild.corp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestAPIs {
    private static final Logger logger = LoggerFactory.getLogger(AuthRestAPIs.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;



    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        JwtResponse jwtResponse = new JwtResponse(jwt);
        jwtResponse.setUsername(loginRequest.getUsername());
        jwtResponse.setId(((User) userDetails).getId());
        for (GrantedAuthority role:userDetails.getAuthorities()) {
            jwtResponse.getAuthorities().add(role.getAuthority());
        }


        return ResponseEntity.ok(jwtResponse);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Void> registerUser(@RequestBody SignUpForm signUpRequest) {
        logger.error("registerUser", signUpRequest);
        try {
            userService.signUpUser(signUpRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UsernameNotFoundException e ){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }


    }


    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ResponseEntity<Void> confirm(@RequestParam String token) {
        logger.error("confirm token {}", token);
        try {
            userService.confirmUser(token);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UsernameNotFoundException e ){
            logger.error("CONFLICT {}", e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (Exception e ){
            logger.error("NOT_ACCEPTABLE {}", e);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }


    }








    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        logger.debug("getAll User");
        List<User> users = userService.findAll();

        if(users.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}