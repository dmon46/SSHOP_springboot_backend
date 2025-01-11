package dmon.SSHOP_springboot_backend.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SecurityUtil {
    /**
     * Get the login of the current user.
     * @return the login of the current user.
     */
    public static Optional<String> getCurrentAccount() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    /**
     * get subject, a claim of jwt payload
     * @param authentication: Authentication
     * @return String
     */
    private static String extractPrincipal(Authentication authentication) {
        //Code: authentication.getPrincipal() instanceof Jwt jwt
        //Mean: if authentication.getPrincipal() has the Jwt type, assign it to the jwt variable
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String s) {
            return s;
        }
        return null;
    }

    /**
     * build scope, a claim of jwt payload
     * @param roles: Set<String>
     * @return String: example "ADMIN_SELLER_USER"
     */
    public static String buildScope(Set<String> roles) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(roles))
            roles.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
