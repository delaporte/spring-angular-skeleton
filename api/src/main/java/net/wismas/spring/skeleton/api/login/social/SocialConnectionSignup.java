package net.wismas.spring.skeleton.api.login.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

/**
 * Created by alexis.delaporte on 15/05/2017.
 */
@Service
public class SocialConnectionSignup implements ConnectionSignUp {
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
//        User user = new User();
//        user.setUsername(connection.getDisplayName());
//        user.setPassword(randomAlphabetic(8));
//        userRepository.save(user);
//        return user.getUsername();

        System.out.println(connection.fetchUserProfile().getEmail());

        return connection.getDisplayName();
    }
}
