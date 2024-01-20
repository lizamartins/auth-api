package com.security.auth.dtos;

import com.security.auth.models.UserRole;

public record RegisterRecordDTO(String username, String password, UserRole role) {
}
