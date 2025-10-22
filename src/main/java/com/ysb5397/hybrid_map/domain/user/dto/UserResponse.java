package com.ysb5397.hybrid_map.domain.user.dto;

import com.ysb5397.hybrid_map.domain.user.User;
import lombok.Builder;
import lombok.Data;

public class UserResponse {

    @Data
    @Builder
    public static class DetailDTO {
        private String username;
        private String email;

        public static DetailDTO fromEntity(User user) {
            return DetailDTO.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private DetailDTO data;
        private String accessToken;

        public LoginDTO(User user, String accessToken) {
            this.data = DetailDTO.fromEntity(user);
            this.accessToken = accessToken;
        }
    }
}
