package com.joao.chatws.services;

import com.joao.chatws.domain.user.LoginRequestDTO;
import com.joao.chatws.domain.user.RegisterRequestDTO;
import com.joao.chatws.domain.user.TokenResponseDTO;
import com.joao.chatws.domain.user.User;
import com.joao.chatws.exceptions.InvalidArgumentException;
import com.joao.chatws.infra.security.TokenService;
import com.joao.chatws.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public TokenResponseDTO register(RegisterRequestDTO data) {
        if (findByEmail(data.email()) != null)
            throw new InvalidArgumentException("E-mail already registered");
        if (!data.password().equals(data.repeatPassword()))
            throw new InvalidArgumentException("Passwords doesn't match");

        User user = new User(data);
        user.setPassword(passwordEncoder.encode(data.password()));
        user = userRepository.save(user);

        return tokenResponse(user);
    }

    public TokenResponseDTO login(LoginRequestDTO data) {
        User user = findByEmail(data.email());
        if (passwordEncoder.matches(data.password(), user.getPassword())) {
            return tokenResponse(user);
        }
        throw new InvalidArgumentException("Invalid credentials");
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    private TokenResponseDTO tokenResponse(User user) {
        return new TokenResponseDTO(user.getFirstName() + " " + user.getLastName(),
                tokenService.generateToken(user));
    }

}
