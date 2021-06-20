package com.wild.corp.service;


import com.wild.corp.configuration.SecurityConfig;
import com.wild.corp.jwt.SignUpForm;
import com.wild.corp.model.ConfirmationToken;
import com.wild.corp.model.Menu;
import com.wild.corp.model.User;
import com.wild.corp.model.UserRole;
import com.wild.corp.repositories.ConfirmationTokenRepository;
import com.wild.corp.repositories.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service("userDetailsService")
@Transactional
public class UserService implements UserDetailsService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void signUpUser(SignUpForm signUpForm) throws UsernameNotFoundException {

        logger.debug("signUpUser +signUpForm.getUsername() "+signUpForm.getUsername());
        logger.debug("signUpUser userRepository.findUserWithName(signUpForm.getUsername()) "+userRepository.findUserWithName(signUpForm.getUsername()));
        logger.debug("signUpUser userRepository.findUserWithName(signUpForm.getUsername()).isPresent() "+userRepository.findUserWithName(signUpForm.getUsername()).isPresent());

        if(!userRepository.findUserWithName(signUpForm.getUsername()).isPresent()) {
            final String encryptedPassword = securityConfig.passwordEncoder().encode(signUpForm.getPassword());

            User user = new User();
            user.setUsername(signUpForm.getUsername());
            user.setEmail(signUpForm.getEmail());
            user.setPassword(encryptedPassword);
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(UserRole.USER);
            if(userRepository.findAll().size() == 0){
                userRoles.add(UserRole.ADMIN);
            }
            user.setUserRoles(userRoles);
            final User createdUser = userRepository.save(user);

            for (int i = 1; i < 53; i++) {
                Menu tmpMenu = new Menu();
                tmpMenu.setNumeroSemaine(i);
                menuService.add(tmpMenu, createdUser);
            }

            final ConfirmationToken confirmationToken = new ConfirmationToken(user);
            sendConfirmationMail(createdUser.getEmail(), confirmationToken.getConfirmationToken());
            confirmationTokenService.saveConfirmationToken(confirmationToken);
        }else{
            throw new UsernameNotFoundException("This userName is already Used");
        }
    }

    public void confirmUser(String token) {
        ConfirmationToken confirmationToken= confirmationTokenRepository.findByconfirmationToken(token);
        final User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

    }

    public void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Confirmation d'adresse email");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText("Merci pour votre inscription. <br/>" +
                "Pour valider votre compte et vous permettre de vous connecter, <br/>"+
                "veuillez cliquer sur le lien ci dessous pour valider votre email. <br/>" +
                "<a href=\"http://pausebricolage.fr/GestionMenu/#/confirm/" + token+"\">http://pausebricolage.fr/GestionMenu/#/confirm/" + token+"</a>");

        emailSenderService.sendEmail(mailMessage);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }



    public List<User> findAll() throws UsernameNotFoundException {

        List<User> users = userRepository.findAll();

        return users;
    }



}