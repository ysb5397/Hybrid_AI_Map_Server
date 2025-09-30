package com.ysb5397.hybrid_map.user;

import lombok.Builder;
import lombok.Data;

public class UserResponse {

    @Data
    @Builder
    public static class SaveDTO {
        private String username;
        private String email;

        public static SaveDTO fromEntity(User user) {
            return SaveDTO.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        }
    }
}
