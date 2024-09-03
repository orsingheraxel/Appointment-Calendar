package com.appointment.diary.a_infraestructure.config.filter;

import com.appointment.diary.a_infraestructure.utils.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtTokenValidator (JwtUtils jwtUtils){
        this.jwtUtils=jwtUtils;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        //obtengo la parte que me importa del token, arrancan con Bearer.
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            //chequeo si el valido el token, caso contrario se rechaza tod
            DecodedJWT decodedJWT = jwtUtils.validateToken(token);

            String username = jwtUtils.extractUsername(decodedJWT);
            String authoritiesUser = jwtUtils.getSpecificClaim(decodedJWT,"authorities").asString();

            //obtengo los permisos separados por comas (READ, CREATE, etc)
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesUser);

            //al ser valido el token, guardo el user y las autorizaciones en SecurityContextHolder
            SecurityContext securityContext = SecurityContextHolder.getContext();

            //le paso el username y los permisos, el password no por seguridad
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
        }
        filterChain.doFilter(request, response);
    }
}
