package com.appointment.diary.b_application.service.impl;

import com.appointment.diary.a_infraestructure.utils.JwtUtils;
import com.appointment.diary.b_application.dto.auth.AuthCreateUserRequest;
import com.appointment.diary.b_application.dto.auth.AuthLoginRequest;
import com.appointment.diary.b_application.dto.auth.AuthResponse;
import com.appointment.diary.b_application.port.out.repository.RoleRepository;
import com.appointment.diary.b_application.port.out.repository.UserRepository;
import com.appointment.diary.c_domain.model.RoleEntity;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.jwtUtils=jwtUtils;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email" + email + " doesn't exist"));

        List<SimpleGrantedAuthority> authorityList = getAuthorities(user);

        return new User(
                user.getEmail(), //funciona like username
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialNonExpired(),
                authorityList);
    }


    //logueo del usuario
    public AuthResponse loginUser(AuthLoginRequest userRequest) {
        //recupero los datos del usuario
        String email = userRequest.email();
        String password = userRequest.password();

        Authentication authentication = this.authenticate(email,password);

        //guardo la autenticacion en securityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //creo el token
        String accesToken = jwtUtils.createToken(authentication);

        return new AuthResponse(email, "User loged succesfully", accesToken, true);
    }

    public Authentication authenticate(String email, String password) {
        //recupero el user de la db
        UserDetails userDetails = this.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }

    //creacion de usuario
    public AuthResponse createUser(AuthCreateUserRequest userRequest) {
        //datos del usuario
        String name = userRequest.name();
        String email = userRequest.email();
        String password = userRequest.password();
        List<String> roleRequest = userRequest.roleRequest().roleListName();
        String phoneNumber = userRequest.phoneNumber();

        //chequear si roles que envio matchean con los de la tabla, puedo enviar CONTRATISTA por ej y ese no existe
        Set<RoleEntity> roleEntitySet = new HashSet<>(roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest));

        if (roleEntitySet.isEmpty()){
            throw new IllegalArgumentException("The roles specified does not exits.");
        }

        //encripto el password
        String encodedPassword = passwordEncoder.encode(password);

        UserEntity userEntity = UserEntity.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .phoneNumber(phoneNumber)
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialNonExpired(true)
                .build();

        //guardo user en db
        UserEntity userCreated = userRepository.save(userEntity);

        List<SimpleGrantedAuthority> authorityList = getAuthorities(userCreated);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getPassword(), authorityList);

        String accessToken = jwtUtils.createToken(authentication);

        // Puedes devolver un AuthResponse con la información que necesites, por ejemplo, el username
        return new AuthResponse(email, "User created successfully",accessToken,true);
    }


    //obtengo roles y permisos
    private ArrayList<SimpleGrantedAuthority> getAuthorities(UserEntity user) {
        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name()))
        );
        user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission ->
                        authorityList.add(new SimpleGrantedAuthority(permission.getName()))
                );
        return authorityList;
    }
}
