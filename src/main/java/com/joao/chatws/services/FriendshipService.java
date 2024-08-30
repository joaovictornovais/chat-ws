package com.joao.chatws.services;

import com.joao.chatws.domain.friendship.AcceptFriendshipRequestDTO;
import com.joao.chatws.domain.friendship.Friendship;
import com.joao.chatws.domain.friendship.SendFriendshipRequestDTO;
import com.joao.chatws.domain.user.User;
import com.joao.chatws.exceptions.EntityNotFoundException;
import com.joao.chatws.exceptions.FriendshipRequestNotForUserException;
import com.joao.chatws.repositories.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    public void sendFriendshipRequest(SendFriendshipRequestDTO data) {
        User sender = userService.findByEmail(data.senderEmail());
        User receiver = userService.findByEmail(data.receiverEmail());

        if (receiver == null) {
            throw new EntityNotFoundException("User doesn't exists");
        }

        friendshipRepository.save(new Friendship(sender, receiver));
    }

    public void acceptFriendshipRequest(AcceptFriendshipRequestDTO data) {
        Friendship friendship = findById(data.friendshipId());
        User receiver = userService.findByEmail(data.receiver());
        if (friendship.getReceiver() != receiver) {
            throw new FriendshipRequestNotForUserException
                    ("You are not the receiver from this friendship request");
        }

        friendship.setAccepted(true);
        friendship.setAcceptedDate(LocalDate.now());
        friendshipRepository.save(friendship);
    }

    public Friendship findById(UUID id) {
        return friendshipRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Friendship Request not found"));
    }

}
