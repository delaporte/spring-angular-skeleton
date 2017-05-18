package net.wismas.spring.skeleton.api.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexisdelaporte on 04/05/2015.
 */
@Component
public class RESTLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //response.setStatus(HttpServletResponse.SC_OK);
        String refererUrl = request.getHeader("Referer");
        response.sendRedirect("/");
    }
}
