package com.joao.chatws.services;

import com.joao.chatws.domain.friendship.AcceptFriendshipRequestDTO;
import com.joao.chatws.domain.friendship.Friendship;
import com.joao.chatws.domain.friendship.SendFriendshipRequestDTO;
import com.joao.chatws.domain.user.User;
import com.joao.chatws.exceptions.EntityNotFoundException;
import com.joao.chatws.exceptions.FriendshipRequestNotForUserException;
import com.joao.chatws.exceptions.InvalidArgumentException;
import com.joao.chatws.repositories.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
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

        if (sender.equals(receiver)) {
            throw new InvalidArgumentException("You cannot send a self request");
        }

        Optional<Friendship> friendship = verifyIfAlreadyFriends(sender, receiver);
        if (friendship.isPresent()) {
            if (friendship.get().isAccepted()) {
                throw new InvalidArgumentException("You already friends");
            }
            if (friendship.get().getSender().equals(sender)) {
                throw new InvalidArgumentException("You already sent a request to this user");
            }
            throw new InvalidArgumentException("This user already sent an request to you. " +
                    "You need to accept it");
        }

        friendshipRepository.save(new Friendship(sender, receiver));
    }

    public Optional<Friendship> verifyIfAlreadyFriends(User user1, User user2) {
        Optional<Friendship> friendship1 = friendshipRepository.findBySenderAndReceiver(user1, user2);
        if (friendship1.isPresent()) {
            return friendship1;
        }

        return friendshipRepository.findBySenderAndReceiver(user2, user1);
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
