package com.thomas15v.crossserver.api;

import java.util.UUID;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface Player {

    UUID getUUID();

    String getName();

    void kick();

    void ban();

    void sendMessage(String message);
}
