package com.bookmanagement.bookmanagementsystem.config;

import com.bookmanagement.bookmanagementsystem.dao.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


        @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (userRepository.findUserByEmail("adminuser@gmail.com").isEmpty()){
//            User user = new User("Admin", "User","adminuser@gmail.com", passwordEncoder.encode("2345"), RoleType.ADMIN);
//            userRepository.save(user);
//        }
    }

}

