package net.wismas.spring.skeleton.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by alexis.delaporte on 15/05/2017.
 */
@Controller
public class GreetingController {
    @RequestMapping("/api/greeting")
    public String greeting() {
        return "greeting";
    }
}
