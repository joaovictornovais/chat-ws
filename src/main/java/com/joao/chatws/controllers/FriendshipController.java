package com.joao.chatws.controllers;

import com.joao.chatws.domain.friendship.AcceptFriendshipRequestDTO;
import com.joao.chatws.domain.friendship.SendFriendshipRequestDTO;
import com.joao.chatws.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/friendship")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping
    public ResponseEntity<Void> sendRequest(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String receiverEmail) {
        friendshipService.sendFriendshipRequest(new SendFriendshipRequestDTO(userDetails.getUsername(), receiverEmail));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{friendshipId}")
    public ResponseEntity<Void> acceptRequest(@AuthenticationPrincipal UserDetails userDetails, @PathVariable UUID friendshipId) {
        AcceptFriendshipRequestDTO data = new AcceptFriendshipRequestDTO(userDetails.getUsername(), friendshipId);
        friendshipService.acceptFriendshipRequest(data);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
