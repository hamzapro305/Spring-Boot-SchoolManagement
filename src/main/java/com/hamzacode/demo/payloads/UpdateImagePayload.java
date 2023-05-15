package com.hamzacode.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class UpdateImagePayload {
    @NotNull(message = "User Id cannot be empty")
    private UUID uid;

    @NotEmpty(message = "photo url cannot be empty")
    private String photoURL;
}
