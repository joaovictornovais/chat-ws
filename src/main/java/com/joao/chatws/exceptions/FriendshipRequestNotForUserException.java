package com.joao.chatws.exceptions;

public class FriendshipRequestNotForUserException extends RuntimeException{
    public FriendshipRequestNotForUserException(String msg) {
        super(msg);
    }
}
