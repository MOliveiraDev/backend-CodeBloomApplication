package com.code.bloom.service.login;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.entity.user.UserStatus;
import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.dto.login.LoginRequest;
import com.code.bloom.dto.login.LoginResponse;
import com.code.bloom.exceptions.login.EmailNotFoundException;
import com.code.bloom.exceptions.login.PasswordIncorretException;
import com.code.bloom.service.jwt.JwtService;
import com.code.bloom.service.jwt.TokenService;
import jakarta.transaction.Transactional;
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
    private final UserRepository userRepository;
    private final TokenService tokenService;


    @Transactional
    public LoginResponse login(LoginRequest request) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            tokenService.saveUserToken(jwtService.generateToken((UserDetails) authentication.getPrincipal()), (UserEntity) authentication.getPrincipal());


            UserEntity user = (UserEntity) authentication.getPrincipal();
            user.setUserStatus(UserStatus.ONLINE);
            userRepository.save(user);

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
