package com.joao.chatws.domain.friendship;

import java.util.UUID;

public record AcceptFriendshipRequestDTO(String receiver, UUID friendshipId) {
}
