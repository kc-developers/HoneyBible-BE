package com.kwangchun.honeybible.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    private final long JWT_EXPIRATION = 60 * 60 * 1000L; // 토큰 유효시간 1시간

    private final String jwtSecret;

    public JwtProvider() {
        this.jwtSecret = createJwtSecret();
    }

    public String generateToken(String username, String ttolae, String lastPhoneNum) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION))
                .claim("username", username)
                .claim("ttolae", ttolae)
                .claim("lastPhoneNum", lastPhoneNum)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private String createJwtSecret() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[24];
            secureRandom.nextBytes(randomBytes);
            Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
            String key = encoder.encodeToString(randomBytes);

            KeySpec spec = new PBEKeySpec(key.toCharArray(), randomBytes, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return encoder.encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }

}

