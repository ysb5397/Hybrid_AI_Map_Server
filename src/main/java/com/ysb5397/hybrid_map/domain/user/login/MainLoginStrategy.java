package com.ysb5397.hybrid_map.domain.user.login;

import com.ysb5397.hybrid_map._global.config.jwt.JwtProvider;
import com.ysb5397.hybrid_map.domain.user.User;
import com.ysb5397.hybrid_map.domain.user.UserLoginType;
import com.ysb5397.hybrid_map.domain.user.dto.UserRequest;
import com.ysb5397.hybrid_map.domain.user.dto.UserResponse;
import com.ysb5397.hybrid_map.domain.user.repository.UserRepository;
import com.ysb5397.hybrid_map.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class MainLoginStrategy implements LoginStrategy {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public UserResponse.LoginDTO login(UserRequest.LoginDTO loginDTO) {
        User loginUser = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new NoSuchElementException("해당하는 유저가 존재하지 않습니다."));

        if(!passwordEncoder.matches(loginDTO.getPassword(), loginUser.getPassword())) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        String token = jwtProvider.createToken(loginUser);
        return new UserResponse.LoginDTO(loginUser, token);
    }

    @Override
    public boolean validate(String type) {
        return UserLoginType.MAIN.name().equalsIgnoreCase(type);
    }
}
