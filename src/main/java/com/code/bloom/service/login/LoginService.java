package com.code.bloom.service.login;

import com.code.bloom.dto.login.LoginRequest;
import com.code.bloom.dto.login.LoginResponse;
import com.code.bloom.exceptions.login.EmailNotFoundException;
import com.code.bloom.exceptions.login.PasswordIncorretException;
import com.code.bloom.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public LoginResponse login(LoginRequest request) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            return new LoginResponse(jwtToken);
        } catch (BadCredentialsException e) {
            throw new PasswordIncorretException("Senha incorreta");
        }   catch (Exception e ) {
            throw new EmailNotFoundException("Email não encontrado");
        }
    }
        }
