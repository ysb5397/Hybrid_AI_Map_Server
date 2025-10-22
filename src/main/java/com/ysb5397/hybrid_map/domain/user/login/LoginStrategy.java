package com.ysb5397.hybrid_map.domain.user.login;

import com.ysb5397.hybrid_map.domain.user.dto.UserRequest;
import com.ysb5397.hybrid_map.domain.user.dto.UserResponse;

public interface LoginStrategy {
    UserResponse.LoginDTO login(UserRequest.LoginDTO loginDTO);
    boolean validate(String type);
}
