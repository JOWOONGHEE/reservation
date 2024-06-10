package com.zerobase.reservation.domain.user.dto;

import lombok.*;
import com.zerobase.reservation.domain.user.entity.UserEntity;
import com.zerobase.reservation.global.enumset.PersonRole;

import java.time.LocalDateTime;

public class UserAddition {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private String username;
        private String password;
        private String phoneNumber;

        public static UserEntity toEntity(Request request) {
            return UserEntity.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .phoneNumber(request.getPhoneNumber())
                    .role(PersonRole.ROLE_USER.toString())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String username;
        private String phoneNumber;

        private String role;

        private LocalDateTime createdAt;

        public static Response fromDto(UserDto dto) {
            return Response.builder()
                    .username(dto.getUsername())
                    .phoneNumber(dto.getPhoneNumber())
                    .role(dto.getRole())
                    .createdAt(dto.getCreatedAt())
                    .build();
        }
    }

}
