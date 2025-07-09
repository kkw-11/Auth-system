package com.security.auth.controller;

import com.security.auth.dto.SignupReq;
import com.security.auth.dto.SignupRes;
import com.security.auth.dto.common.ApiResponse;
import com.security.auth.dto.common.ResponseCode;
import com.security.auth.service.UserService;
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
    public ResponseEntity<ApiResponse<SignupRes>> signup(@Valid @RequestBody SignupReq request) {
        SignupRes signup = userService.signup(request);
        return ResponseEntity
                .status(ResponseCode.SIGNUP_SUCCESS.getHttpStatus())
                .body(ApiResponse.success(ResponseCode.SIGNUP_SUCCESS, signup));

    }
}
