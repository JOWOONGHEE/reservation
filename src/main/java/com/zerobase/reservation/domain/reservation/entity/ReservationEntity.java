package com.zerobase.reservation.domain.reservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import com.zerobase.reservation.domain.manager.entity.ManagerEntity;
import com.zerobase.reservation.global.enumset.ReservationStatus;
import com.zerobase.reservation.domain.restaurant.entity.RestaurantEntity;
import com.zerobase.reservation.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RESERVATION")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // 매니저와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;

    // 레스토랑과 다대일 관계
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    private Integer headCount;      // 매장 이용 인원 수

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @LastModifiedDate
    private LocalDateTime statusModifiedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime reservationDateTime;

}
