package com.zerobase.reservation.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.zerobase.reservation.domain.manager.entity.ManagerEntity;
import com.zerobase.reservation.domain.reservation.dto.ReservationDto;
import com.zerobase.reservation.domain.reservation.dto.ReservationStatusChange;
import com.zerobase.reservation.domain.reservation.service.ReservationService;
import com.zerobase.reservation.global.enumset.ReservationStatus;
import com.zerobase.reservation.global.error.CustomException;

import static com.zerobase.reservation.global.error.CustomErrorCode.MANAGER_NAME_MISMATCH;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class ReservationManagerController {

    private final ReservationService reservationService;

    /**
     * 예약 상태 변경
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/manager/reservation/{managerName}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable String managerName,
                                                     @RequestBody ReservationStatusChange statusChange,
                                                     @AuthenticationPrincipal ManagerEntity manager
    ) {
        String currentManagerName = manager.getUsername();
        if(!managerName.equals(currentManagerName)) {
            throw new CustomException(MANAGER_NAME_MISMATCH);
        }
        reservationService.changeReservationStatus(managerName, ReservationStatus.of(statusChange.getStatus()));
        return ResponseEntity.ok(reservationService.reservationInformationByManager(managerName));
    }

    /**
     * 예약 정보 확인
     */
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manager/reservation/{managerName}/detail")
    public ResponseEntity<?> reservationDetail(@PathVariable String managerName,
                                               @AuthenticationPrincipal ManagerEntity manager
    ) {
        String currentManagerName = manager.getUsername();
        if(!managerName.equals(currentManagerName)) {
            throw new CustomException(MANAGER_NAME_MISMATCH);
        }
        ReservationDto reservationDto = reservationService.reservationInformationByManager(managerName);
        return ResponseEntity.ok(reservationDto);
    }
}
