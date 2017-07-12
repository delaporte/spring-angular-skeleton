package net.wismas.spring.skeleton.api.login.dao;

import net.wismas.spring.skeleton.api.user.UserEntity;
import net.wismas.spring.skeleton.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexisdelaporte on 16/12/2016.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        List<UserEntity> byLogin = userRepository.findByLogin(login);
        if (byLogin.size() == 0) {
            throw new UsernameNotFoundException("Username not found");
        }
        UserEntity user = byLogin.get(0);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                isValid(user), true, true, true, getGrantedAuthorities(user));
    }

    private boolean isValid(UserEntity user) {
        //On peut ici filtrer pour désactiver certains utilisateurs
        return true;
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserEntity user) {
        //On peut ici mettre des roles particuliers en fonction de certaines conditions
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
