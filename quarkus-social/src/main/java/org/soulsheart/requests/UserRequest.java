package org.soulsheart.requests;

import java.time.LocalDate;

public record UserRequest(
        String fullName,
        LocalDate birthDate,
        String email,
        String password
) {
}
