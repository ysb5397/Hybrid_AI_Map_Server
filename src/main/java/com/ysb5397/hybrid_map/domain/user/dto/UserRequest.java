package com.ysb5397.hybrid_map.domain.user.dto;

import com.ysb5397.hybrid_map.domain.user.UserGender;
import lombok.Data;

public class UserRequest {

    @Data
    public static class SaveDTO {
        private String email;
        private String username;
        private String password;
        private String repeatPassword;
        private UserGender userGender;
        private Long age;
    }

    @Data
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
