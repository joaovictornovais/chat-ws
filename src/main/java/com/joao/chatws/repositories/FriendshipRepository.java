package com.joao.chatws.repositories;

import com.joao.chatws.domain.friendship.Friendship;
import com.joao.chatws.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    Optional<Friendship> findBySenderAndReceiver(User sender, User receiver);

}
