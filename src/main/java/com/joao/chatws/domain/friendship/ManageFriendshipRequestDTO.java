package com.joao.chatws.domain.friendship;

import java.util.UUID;

public record ManageFriendshipRequestDTO(String userEmail, UUID friendshipId) {
}
