package org.soulsheart.requests;

import java.time.LocalDate;

public record UserRequest(
        String fistName,
        String lastName,
        LocalDate birthDate,
        String email,
        String password
) {
}
