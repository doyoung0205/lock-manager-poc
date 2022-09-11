package me.doyoung.lockmanagerpoc.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.doyoung.lockmanagerpoc.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    public static class SaveRequest {
        private String loginId;
        private String password;
        private String name;

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .password(password)
                    .loginId(loginId)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    public static class Response {
        private String loginId;
        private String name;

        public static Response from(User user) {
            return new UserDto.Response(user.getLoginId(), user.getName());
        }
    }
}
