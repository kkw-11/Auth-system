package com.security.auth.repository;

import com.security.auth.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("이메일로 사용자 조회")
    void findByEmail_shouldReturnUser() {
        // given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setNickname("testUser");
        userRepository.save(user);

        // when
        Optional<User> result = userRepository.findByEmail("test@example.com");

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
    }


    @Test
    @DisplayName("회원 저장 및 조회 성공")
    void saveAndFindUser() {
        // given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setNickname("Tester");

        // when
        userRepository.save(user);

        Optional<User> savedUser = userRepository.findByEmail("test@example.com");

        // then
        assertThat(savedUser).isPresent();
        assertThat(savedUser.get().getEmail()).isEqualTo("test@example.com");
        assertThat(savedUser.get().getNickname()).isEqualTo("Tester");
    }

    @Test
    @DisplayName("존재하지 않는 이메일 조회 시 Optional.empty 반환")
    void findByEmailReturnsEmpty() {
        // when
        Optional<User> user = userRepository.findByEmail("nonexistent@example.com");

        // then
        assertThat(user).isNotPresent();
    }
}
