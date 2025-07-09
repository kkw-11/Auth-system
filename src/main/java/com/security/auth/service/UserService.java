package com.security.auth.service;

import com.security.auth.dto.SignupReq;
import com.security.auth.dto.SignupRes;
import com.security.auth.entity.User;
import com.security.auth.exception.exceptions.DuplicateEmailException;
import com.security.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public SignupRes signup(SignupReq request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateEmailException();
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setNickname(request.getNickname());

        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }
}

