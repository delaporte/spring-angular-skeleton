package net.wismas.spring.skeleton.api.controller;

import net.wismas.spring.skeleton.api.user.UserEntity;
import net.wismas.spring.skeleton.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by conta on 18/05/2017.
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    Authentication isAuthentified() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String email = auth.getName();

        return auth;
    }

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method= RequestMethod.POST, value = "/signup")
    public @ResponseBody
    UserEntity createAccount(@RequestBody UserEntity userEntity) {
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));

        return userRepository.save(userEntity);
    }
}
