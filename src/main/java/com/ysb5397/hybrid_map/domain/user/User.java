package com.ysb5397.hybrid_map.domain.user;

import com.ysb5397.hybrid_map._global.util.TimeFormatter;
import com.ysb5397.hybrid_map.domain.user.dto.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "user_tb")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    private Long age;
    private String imageUrl;
    private Timestamp createdAt;

    // 시간 포맷팅 메서드
    public String getTime() {
        return TimeFormatter.getTime(this.createdAt);
    }

    // 유효성 검사
    public void signUpValidate(UserRequest.SaveDTO saveDTO) {
        // 1. 비밀번호 일치 여부 검사
        isPasswordSame(saveDTO.getPassword(), saveDTO.getRepeatPassword());

        // 2. 성별 유효성 검사
        checkGender(saveDTO.getUserGender().name());
    }

    private void isPasswordSame(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) throw new IllegalArgumentException("비밀번호가 서로 일치하지 않습니다.");
    }

    private void checkGender(String userGender) {
        boolean isOkay = false;
        for (UserGender tempGender : UserGender.values()) {
            if (tempGender.toString().equalsIgnoreCase(userGender)) isOkay = true;
        }

        if (!isOkay) throw new IllegalArgumentException("성별이 유효하지 않습니다");
    }
}
