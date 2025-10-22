package com.ysb5397.hybrid_map.domain.user.login;

import com.ysb5397.hybrid_map._global.config.HttpConfig;
import com.ysb5397.hybrid_map._global.config.jwt.JwtProvider;
import com.ysb5397.hybrid_map.domain.user.User;
import com.ysb5397.hybrid_map.domain.user.UserLoginType;
import com.ysb5397.hybrid_map.domain.user.dto.UserRequest;
import com.ysb5397.hybrid_map.domain.user.dto.UserResponse;
import com.ysb5397.hybrid_map.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;

@Component
public class KakaoLoginStrategy implements LoginStrategy {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final HttpConfig httpConfig;
    private final String KAKAO_LOGIN_KEY;
    private final String REDIRECT_URI;

    private KakaoLoginStrategy(@Value("${login.kakao.key}") String key,
                               @Value("${login.kakao.redirect-uri}") String uri,
                               UserRepository userRepository,
                               JwtProvider jwtProvider,
                               HttpConfig httpConfig) {

        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.httpConfig = httpConfig;
        this.KAKAO_LOGIN_KEY = key;
        this.REDIRECT_URI = uri;
    }

    @Override
    public UserResponse.LoginDTO login(UserRequest.LoginDTO loginDTO) {
         User loginUser = userRepository.save(kakaoHelper(loginDTO));
         return new UserResponse.LoginDTO(loginUser, jwtProvider.createToken(loginUser));
    }

    @Override
    public boolean validate(String type) {
        return UserLoginType.KAKAO.name().equalsIgnoreCase(type);
    }

    // 카카오 서버랑 통신하는 메서드
    private User kakaoHelper(UserRequest.LoginDTO loginDTO) {
        ResponseEntity<?> response = httpConfig.post(
                "https://kauth.kakao.com/oauth/authorize?client_id=" + KAKAO_LOGIN_KEY +
                "&redirect_uri=" + REDIRECT_URI + "&response_type=code", loginDTO);

        System.out.println(response.getBody());
        return null;
    }
}
