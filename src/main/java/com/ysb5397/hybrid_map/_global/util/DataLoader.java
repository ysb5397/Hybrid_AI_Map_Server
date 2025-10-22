package com.ysb5397.hybrid_map._global.util;

import com.ysb5397.hybrid_map.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// 데이터를 미리 주입시켜주는 구현 클래스
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

    }

}
