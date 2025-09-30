package com.ysb5397.hybrid_map.user;

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
        private String imageUrl;
    }
}
