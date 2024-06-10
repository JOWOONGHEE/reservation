package com.zerobase.reservation.domain.reservation.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import com.zerobase.reservation.domain.reservation.entity.ReservationEntity;
import com.zerobase.reservation.global.enumset.ReservationStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReservationDto {

    private String username;
    private String phoneNumber;

    private String managerName;
    private String restaurantName;

    private Integer headCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime statusModifiedAt;

    private LocalDateTime reservationDateTime;

    public static ReservationDto toDto(ReservationEntity entity) {
        return ReservationDto.builder()
                .username(entity.getUser().getUsername())
                .phoneNumber(entity.getUser().getPhoneNumber())
                .managerName(entity.getManager().getUsername())
                .restaurantName(entity.getRestaurant().getRestaurantName())
                .headCount(entity.getHeadCount())
                .status(entity.getStatus())
                .statusModifiedAt(entity.getStatusModifiedAt())
                .reservationDateTime(entity.getReservationDateTime())
                .build();
    }

}
