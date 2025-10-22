package com.ysb5397.hybrid_map.domain.user.controller;

import com.ysb5397.hybrid_map._global.util.ApiUtil;
import com.ysb5397.hybrid_map.domain.user.dto.UserRequest;
import com.ysb5397.hybrid_map.domain.user.dto.UserResponse;
import com.ysb5397.hybrid_map.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequest.SaveDTO saveDTO) {
        UserResponse.DetailDTO savedUser = userService.signUp(saveDTO);
        return ResponseEntity.ok().body(new ApiUtil<>().success(savedUser));
    }

    // 로그인
    @PostMapping("/login/{type}")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO loginDTO,
                                   @PathVariable("type") String type,
                                   HttpServletResponse response) {
        UserResponse.LoginDTO loginUser = userService.login(loginDTO, type);
        response.setHeader("Authorization", "Bearer " + loginUser.getAccessToken());
        return ResponseEntity.ok().body(new ApiUtil<>().success(loginUser));
    }

    // 카카오톡 토큰 인가 요청
    @PostMapping("/login/kakao/auth")
    public ResponseEntity<?> confirmToken(@RequestBody String code) {
        System.out.println(code);
        return ResponseEntity.ok().body(new ApiUtil<>().success("인증이 완료되었습니다."));
    }

    // 로그아웃
    // 마이페이지 조회
    @GetMapping("/{userId}/my-profile")
    public ResponseEntity<?> getMyProfile(@PathVariable("userId") Long userId,
                                          @RequestAttribute("userEmail") String userEmail) {
        UserResponse.DetailDTO userDetail = userService.getMyProfile(userId, userEmail);
        return ResponseEntity.ok().body(new ApiUtil<>().success(userDetail));
    }

    // 프로필 이미지 변경
}
