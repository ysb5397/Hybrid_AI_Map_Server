package com.ysb5397.hybrid_map.domain.user.service;

import com.ysb5397.hybrid_map.domain.user.User;
import com.ysb5397.hybrid_map.domain.user.dto.UserRequest;
import com.ysb5397.hybrid_map.domain.user.dto.UserResponse;
import com.ysb5397.hybrid_map.domain.user.login.LoginStrategy;
import com.ysb5397.hybrid_map.domain.user.login.LoginStrategyFactory;
import com.ysb5397.hybrid_map.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginStrategyFactory loginStrategyFactory;

    @Transactional
    public UserResponse.DetailDTO signUp(UserRequest.SaveDTO saveDTO) {
        if(userRepository.findByEmail(saveDTO.getEmail()).isPresent()) throw new IllegalArgumentException("이미 존재하는 사용자입니다.");

        User newUser = new User();
        newUser.signUpValidate(saveDTO);
        newUser = userRepository.save(User.builder()
                .username(saveDTO.getUsername())
                .email(saveDTO.getEmail())
                .password(passwordEncoder.encode(saveDTO.getPassword()))
                .age(saveDTO.getAge())
                .gender(saveDTO.getUserGender())
                .build());

        return UserResponse.DetailDTO.fromEntity(newUser);
    }

    public UserResponse.LoginDTO login(UserRequest.LoginDTO loginDTO, String type) {
        LoginStrategy loginStrategy = loginStrategyFactory.find(type);
        return loginStrategy.login(loginDTO);
    }

    public UserResponse.DetailDTO getMyProfile(Long userId, String userEmail) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 유저가 없습니다."));

        if (!findUser.getEmail().equals(userEmail)) throw new IllegalArgumentException("본인의 정보만 조회 가능합니다.");
        return UserResponse.DetailDTO.fromEntity(findUser);
    }
}
