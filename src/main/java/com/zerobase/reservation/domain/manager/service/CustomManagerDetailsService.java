package com.zerobase.reservation.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.zerobase.reservation.domain.manager.dto.CustomManagerDetails;
import com.zerobase.reservation.domain.manager.entity.ManagerEntity;
import com.zerobase.reservation.domain.manager.repository.ManagerRepository;

@Service
@RequiredArgsConstructor
public class CustomManagerDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // DB 조회
        ManagerEntity managerEntity = managerRepository.findByUsername(username);

        // UserDetail 에 담아서 return 하면 AuthenticationManager 가 검증
        if (managerEntity != null) {
            return new CustomManagerDetails(managerEntity);
        }

        return null;
    }

}
