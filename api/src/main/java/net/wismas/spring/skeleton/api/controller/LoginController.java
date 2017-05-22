package net.wismas.spring.skeleton.api.controller;

import com.google.common.collect.Lists;
import net.wismas.spring.skeleton.api.login.jpa.UserEntity;
import net.wismas.spring.skeleton.api.login.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private UserRepository userRepository;

    @RequestMapping(method= RequestMethod.GET, value = "/test")
    public @ResponseBody
    List<UserEntity> getAllLogin() {
        return Lists.newArrayList(userRepository.findAll());
    }


}
