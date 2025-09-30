package com.ysb5397.hybrid_map.user.service;

import com.ysb5397.hybrid_map.user.User;
import com.ysb5397.hybrid_map.user.UserRequest;
import com.ysb5397.hybrid_map.user.UserResponse;
import com.ysb5397.hybrid_map.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse.SaveDTO save(UserRequest.SaveDTO saveDTO) {
        User user = userRepository.findByEmail(saveDTO.getEmail());

        if (user != null) {
            return null;
        }

        user = userRepository.save(User.builder()
                .username(saveDTO.getUsername())
                .email(saveDTO.getEmail())
                .password(saveDTO.getPassword())
                .age(saveDTO.getAge())
                .imageUrl(saveDTO.getImageUrl())
                .gender(saveDTO.getUserGender())
                .build());

        return UserResponse.SaveDTO.fromEntity(user);
    }
}
