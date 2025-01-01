package dmon.SSHOP_springboot_backend.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import dmon.SSHOP_springboot_backend.entity.account.Account;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SecurityHelper {
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${sshop.jwt.secret-key}")
    String SECRET_KEY;

    public String hashPassword(String plain) {
        return this.passwordEncoder.encode(plain);
    }

    public boolean matchPassword(String plain, String hash) { return this.passwordEncoder.matches(plain, hash); }

    public String genToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getId())
                .issuer("sshop.dmon.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(400, ChronoUnit.DAYS).toEpochMilli()
                ))
                .claim("scope", SecurityUtil.buildScope(account.getRoles()))
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
}
