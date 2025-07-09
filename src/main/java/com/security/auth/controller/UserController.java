package com.security.auth.controller;

import com.security.auth.dto.SignupRequest;
import com.security.auth.dto.common.ApiResponse;
import com.security.auth.service.UserService;
import com.security.auth.success.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@Valid @RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity
                .status(SuccessCode.SIGNUP_SUCCESS.getStatus())
                .body(ApiResponse.success(null, SuccessCode.SIGNUP_SUCCESS.getCode(), SuccessCode.SIGNUP_SUCCESS.getMessage(), SuccessCode.SIGNUP_SUCCESS.getStatus()));

    }
}
