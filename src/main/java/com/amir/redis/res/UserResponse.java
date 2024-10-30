package com.amir.redis.res;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(Long id, String fullName, String email, LocalDate dob, int age) {
}
