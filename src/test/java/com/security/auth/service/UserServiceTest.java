package com.security.auth.service;

import com.security.auth.dto.SignupReq;
import com.security.auth.entity.User;
import com.security.auth.exception.exceptions.DuplicateEmailException;
import com.security.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 성공 - 새로운 이메일")
    void signup_success() {
        // given
        SignupReq request = new SignupReq();
        request.setEmail("newuser@example.com");
        request.setPassword("password123");
        request.setNickname("newuser");

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        // when
        userService.signup(request);

        // then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("회원가입 실패 - 중복 이메일")
    void signup_fail_duplicateEmail() {
        // given
        SignupReq request = new SignupReq();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        request.setNickname("existingUser");

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new User()));

        // when & then
        assertThatThrownBy(() -> userService.signup(request))
                .isInstanceOf(DuplicateEmailException.class)
                .hasMessage("이미 가입된 이메일입니다.");

        verify(userRepository, never()).save(any(User.class));
    }
}
