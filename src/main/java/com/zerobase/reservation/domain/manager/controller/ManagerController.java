package com.zerobase.reservation.domain.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.zerobase.reservation.domain.manager.dto.ManagerAddition;
import com.zerobase.reservation.domain.manager.dto.ManagerDto;
import com.zerobase.reservation.domain.manager.entity.ManagerEntity;
import com.zerobase.reservation.domain.manager.service.ManagerService;
import com.zerobase.reservation.domain.restaurant.dto.RestaurantAddition;
import com.zerobase.reservation.domain.restaurant.dto.RestaurantDto;
import com.zerobase.reservation.domain.restaurant.dto.RestaurantModification;
import com.zerobase.reservation.global.error.CustomException;

import static com.zerobase.reservation.global.error.CustomErrorCode.MANAGER_DOES_NOT_SAME;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 매니저 회원가입
     */
    @PostMapping("/signup/manager")
    public ResponseEntity<?> managerSignup(@RequestBody ManagerAddition.Request request) {
        ManagerDto managerDto = managerService.signup(request);
        return ResponseEntity.ok(ManagerAddition.Response.fromDto(managerDto));
    }

    /**
     * 매니저의 매장 등록
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/manager/restaurant-registration/{username}")
    public ResponseEntity<?> registerRestaurant(@PathVariable String username,
                                                @RequestBody RestaurantAddition.Request request,
                                                @AuthenticationPrincipal ManagerEntity manager
    ) {
        if(!username.equals(manager.getUsername())) {
            throw new CustomException(MANAGER_DOES_NOT_SAME);
        }
        RestaurantDto restaurantDto = managerService.addRestaurant(request);
        return ResponseEntity.ok(RestaurantAddition.Response.fromDto(restaurantDto));
    }

    /**
     * 매니저의 매장 수정
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/manager/restaurant-modification/{username}")
    public ResponseEntity<?> modifyRestaurant(@PathVariable String username,
                                              @RequestBody RestaurantModification.Request request,
                                              @AuthenticationPrincipal ManagerEntity manager
    ) {
        if(!username.equals(manager.getUsername())) {
            throw new CustomException(MANAGER_DOES_NOT_SAME);
        }
        RestaurantDto restaurantDto = managerService.modifyRestaurant(request);
        return ResponseEntity.ok(RestaurantModification.Response.fromDto(restaurantDto));
    }

}
