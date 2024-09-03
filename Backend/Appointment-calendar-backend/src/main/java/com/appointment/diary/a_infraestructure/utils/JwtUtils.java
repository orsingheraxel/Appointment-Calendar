package com.appointment.diary.a_infraestructure.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class JwtUtils {
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    @Value("${security.jwt.key}")
    private String key;

    //CREACION DEL TOKEN
    public String createToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.key);

        String username = authentication.getPrincipal().toString();

        //Obtiene todas las autorizaciones y las guarda como string separados por , (debido a que los claims son asi)
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    //VALIDACION Y RETORNO DE TOKEN DECODIFICADO
    public DecodedJWT validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.key);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            return verifier.verify(token);

        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token invalid, not authortized");
        }
    }

    //EXTRAER USER QUE VIENE DENTRO DEL TOKEN
    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    //DATO ESPECIFICO
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
