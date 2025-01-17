package com.dmon.sshop._infrastructure.security.impl;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._infrastructure.security.ISecurityInfraHelper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor //create a constructor for final and @NonNull fields
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SecurityInfraHelperImpl implements ISecurityInfraHelper {
    @NonFinal
    @Value("${sshop.jwt.secret-key}")
    String SECRET_KEY;

    PasswordEncoder passwordEncoder;

    //PASSWORD//
    @Override
    public String hashPassword(String plain) {
        return this.passwordEncoder.encode(plain);
    }

    @Override
    public boolean matchPassword(String plain, String hash) {
        return this.passwordEncoder.matches(plain, hash);
    }

    //JWT//
    @Override
    public String genToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getId())
                .issuer("sshop.dmon.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(400, ChronoUnit.DAYS).toEpochMilli()
                ))
                .claim("scope", this.buildScope(account.getRoles()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the subject, a claim of jwt payload
     *
     * @return the login of the current user.
     */
    @Override
    public String getAccountId() {
        //1. get Security Context
        SecurityContext securityContext = SecurityContextHolder.getContext();

        //2. get the subject, a claim of jwt payload
        Optional<String> subject = Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));

        return subject.orElseThrow(() -> new AppException(ErrorCode.SECURITY__NOT_IN_SECURITY));
    }


    //HELPER//
    /**
     * extract the subject, a claim of jwt payload, from jwt principal
     *
     * @param authentication: Authentication
     * @return String
     */
    private String extractPrincipal(Authentication authentication) {
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
     *
     * @param roles: Set<String>
     * @return String: example "ADMIN_SELLER_USER"
     */
    private String buildScope(Set<String> roles) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(roles))
            roles.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
