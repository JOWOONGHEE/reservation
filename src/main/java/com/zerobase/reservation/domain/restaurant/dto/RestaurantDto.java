package com.zerobase.reservation.domain.restaurant.dto;

import lombok.*;
import com.zerobase.reservation.domain.restaurant.entity.RestaurantEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantDto {

    private String managerName;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetail;

    private double lat;
    private double lnt;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static RestaurantDto toDto(RestaurantEntity entity) {
        return RestaurantDto.builder()
                .managerName(entity.getManager().getUsername())
                .restaurantName(entity.getRestaurantName())
                .restaurantAddress(entity.getRestaurantAddress())
                .restaurantDetail(entity.getRestaurantDetail())
                .lat(entity.getLat())
                .lnt(entity.getLnt())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .build();
    }

}
