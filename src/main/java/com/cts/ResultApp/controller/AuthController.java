package com.cts.ResultApp.controller;

import com.cts.ResultApp.dto.SignupRequest;
import com.cts.ResultApp.model.User;
import com.cts.ResultApp.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final ResultService resultService;
    private final MessageSource messageSource;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User newUser = resultService.registerUser(signUpRequest);
        String message = messageSource.getMessage("info.user.registered",
                new Object[]{newUser.getId()}, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * Unified validation endpoint for both Students and Teachers.
     */
    @GetMapping("/validate")
    public ResponseEntity<?> validateLogin(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("username", authentication.getName());
        response.put("authorities", authentication.getAuthorities());
        return ResponseEntity.ok(response);
    }
}
