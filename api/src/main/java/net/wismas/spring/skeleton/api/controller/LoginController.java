package net.wismas.spring.skeleton.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by conta on 18/05/2017.
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    Authentication isAuthentified() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String email = auth.getName();

        return auth;
    }
}
