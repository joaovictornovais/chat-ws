package com.joao.chatws.domain.friendship;

import com.joao.chatws.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "friendship")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private boolean isAccepted;

    private LocalDate acceptedDate;

    public Friendship(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        isAccepted = false;
        acceptedDate = null;
    }

}
