package com.joao.chatws.repositories;

import com.joao.chatws.domain.friendship.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {
}
