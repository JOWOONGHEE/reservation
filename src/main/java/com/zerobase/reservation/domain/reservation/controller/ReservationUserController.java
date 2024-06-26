package com.zerobase.reservation.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.zerobase.reservation.domain.reservation.dto.ReservationAddition;
import com.zerobase.reservation.domain.reservation.dto.ReservationArrivalCheck;
import com.zerobase.reservation.domain.reservation.dto.ReservationDto;
import com.zerobase.reservation.domain.reservation.dto.UserArrival;
import com.zerobase.reservation.domain.reservation.service.ReservationService;
import com.zerobase.reservation.domain.user.entity.UserEntity;
import com.zerobase.reservation.global.error.CustomErrorCode;
import com.zerobase.reservation.global.error.CustomException;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class ReservationUserController {

    private final ReservationService reservationService;

    /**
     * 레스토랑 예약
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/user/reservation/request")
    public ResponseEntity<?> addReservation(@RequestBody ReservationAddition.Request request,
                                            @AuthenticationPrincipal UserEntity user
    ) {
        String currentUsername = user.getUsername();
        if(!request.getUsername().equals(currentUsername)) {
            throw new CustomException(CustomErrorCode.USERNAME_MISMATCH);
        }
        ReservationDto reservationDto = reservationService.addReservation(request);
        return ResponseEntity.ok(ReservationAddition.Response.fromDto(reservationDto));
    }



    /**
     * 레스토랑 도착 확인
     */
    @PostMapping("/reservation/arrival")
    public ResponseEntity<?> checkUserArrival(@RequestBody ReservationArrivalCheck arrivalCheck) {
        ReservationDto reservationDto = reservationService.checkUserArrival(arrivalCheck.getUsername(), arrivalCheck.getPhoneNumber());
        return ResponseEntity.ok(new UserArrival(reservationDto));
    }

    /**
     * 예약 정보 확인
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/reservation/{username}/detail")
    public ResponseEntity<?> reservationDetail(@PathVariable String username,
                                               @AuthenticationPrincipal UserEntity user
    ) {
        String currentUsername = user.getUsername();
        if(!username.equals(currentUsername)) {
            throw new CustomException(CustomErrorCode.USERNAME_MISMATCH);
        }
        ReservationDto reservationDto = reservationService.reservationInformationByUser(username);
        return ResponseEntity.ok(reservationDto);
    }

}
