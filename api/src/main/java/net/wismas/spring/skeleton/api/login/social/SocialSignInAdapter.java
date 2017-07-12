package net.wismas.spring.skeleton.api.login.social;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;

/**
 * Created by alexis.delaporte on 15/05/2017.
 */
public class SocialSignInAdapter implements SignInAdapter {
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        connection.fetchUserProfile().getEmail(), null,
                        Arrays.asList(new SimpleGrantedAuthority("SOCIAL_USER"))));

        return null;
    }
}
