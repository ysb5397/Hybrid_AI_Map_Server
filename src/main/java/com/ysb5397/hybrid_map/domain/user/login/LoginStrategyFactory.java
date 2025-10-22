package com.ysb5397.hybrid_map.domain.user.login;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginStrategyFactory {

    private final List<LoginStrategy> loginStrategies;

    public LoginStrategy find(String type) {
        for (LoginStrategy loginStrategy : loginStrategies) {
            if(loginStrategy.validate(type)) return loginStrategy;
        }

        throw new IllegalArgumentException("올바른 로그인 타입이 아닙니다.");
    }
}
