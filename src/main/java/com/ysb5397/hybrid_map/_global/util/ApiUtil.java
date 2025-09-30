package com.ysb5397.hybrid_map._global.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApiUtil<T> {

    private int statusCode;
    private String msg;
    private T body;

    // 정상 응답 코드
    public ApiUtil success(T body) {
        return new ApiUtil<>(200, "응답 성공", body);
    }

    // 실패 응답 코드
    public ApiUtil fail() {
        return new ApiUtil<>(500, "응답 실패", null);
    }
}
