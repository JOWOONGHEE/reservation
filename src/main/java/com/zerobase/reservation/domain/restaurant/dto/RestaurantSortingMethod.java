package com.zerobase.reservation.domain.restaurant.dto;

import lombok.*;
import com.zerobase.reservation.global.enumset.SortRestaurant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RestaurantSortingMethod {

    private String restaurantName;

    private SortRestaurant sort;

    private double lat;
    private double lnt;

}
