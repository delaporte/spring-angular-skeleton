package net.wismas.spring.skeleton.api.login.social.google;

import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by conta on 18/05/2017.
 */
@ConfigurationProperties(prefix = "spring.social.google")
public class GoogleProperties extends SocialProperties {
}
