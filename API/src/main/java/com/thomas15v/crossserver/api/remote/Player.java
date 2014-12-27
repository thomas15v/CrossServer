package com.thomas15v.crossserver.api.remote;

import java.util.UUID;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface Player {

    UUID getUUID();

    String getName();

    void kick(String kickMessage);

    void ban(String banMessage);

    void sendMessage(String message);

    Server getServer();
}
