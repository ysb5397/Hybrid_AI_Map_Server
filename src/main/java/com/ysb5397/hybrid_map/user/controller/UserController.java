package com.ysb5397.hybrid_map.user.controller;

import com.ysb5397.hybrid_map._global.util.ApiUtil;
import com.ysb5397.hybrid_map.user.UserRequest;
import com.ysb5397.hybrid_map.user.UserResponse;
import com.ysb5397.hybrid_map.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    public ResponseEntity<?> join(@RequestBody UserRequest.SaveDTO saveDTO) {
        UserResponse.SaveDTO savedUser = userService.save(saveDTO);
        return savedUser == null ? ResponseEntity.ok().body(new ApiUtil<>().fail()) : ResponseEntity.ok().body(new ApiUtil<>().success(savedUser));
    }

    // 로그인
    // 로그아웃
    // 마이페이지 조회
    // 프로필 이미지 변경
}
