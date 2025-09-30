package com.ysb5397.hybrid_map.user;

import com.ysb5397.hybrid_map._global.util.TimeFormatter;
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

    public String getTime() {
        return TimeFormatter.getTime(this.createdAt);
    }
}
