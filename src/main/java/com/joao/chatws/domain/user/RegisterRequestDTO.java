package com.joao.chatws.domain.user;

public record RegisterRequestDTO(String firstName,
                                 String lastName,
                                 String email,
                                 String password,
                                 String repeatPassword
) {
}
