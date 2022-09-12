package me.doyoung.lockmanagerpoc.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.doyoung.lockmanagerpoc.domain.user.User;

public final class UserDto {
    private UserDto() {
    }


    public static class SaveRequest {
        private String loginId;
        private String password;
        private String name;

        private SaveRequest() {
        }

        public SaveRequest(String loginId, String password, String name) {
            this.loginId = loginId;
            this.password = password;
            this.name = name;
        }

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .password(password)
                    .loginId(loginId)
                    .build();
        }

        public String getLoginId() {
            return loginId;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "SaveRequest{" +
                    "loginId='" + loginId + '\'' +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class Response {
        private String loginId;
        private String name;

        private Response() {
        }

        public Response(String loginId, String name) {
            this.loginId = loginId;
            this.name = name;
        }

        public static Response from(User user) {
            return new UserDto.Response(user.getLoginId(), user.getName());
        }

        public String getLoginId() {
            return loginId;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "loginId='" + loginId + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
