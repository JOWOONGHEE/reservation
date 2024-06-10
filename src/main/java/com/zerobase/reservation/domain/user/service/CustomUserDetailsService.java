package com.zerobase.reservation.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.zerobase.reservation.domain.user.dto.CustomUserDetails;
import com.zerobase.reservation.domain.user.entity.UserEntity;
import com.zerobase.reservation.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // DB 조회
        UserEntity userEntity = userRepository.findByUsername(username);

        // UserDetail 에 담아서 return 하면 AuthenticationManager 가 검증
        if (userEntity != null) {
            return new CustomUserDetails(userEntity);
        }

        return null;
    }

}
