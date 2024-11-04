package com.uncode.stop.rest_api.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioService usuarioService;

    public String authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        final var userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final var jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    public String register(Usuario usuario) {
        usuario.setClave(usuario.getClave().trim());
        if (usuario.getClave().length() < 6) {
            throw new ServiceException("password length");
        }
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuarioService.create(usuario);
        return "User registered";
    }

}
