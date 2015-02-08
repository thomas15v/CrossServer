package com.thomas15v.crossserver.api.remote.nullremotes;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Created by thomas15v on 19/01/15.
 */
@RequiredArgsConstructor
public class NullPlayer implements Player {

    @Getter
    @NonNull
    private String name;

    @Override
    public UUID getUUID() {
        return UUID.fromString(name);
    }

    @Override
    public void kick(String kickMessage) {}

    @Override
    public void ban(String banMessage) {}

    @Override
    public void sendMessage(String message) {}

    @Override
    public Server getServer() {
        return new NullServer("NullServer");
    }

    @Override
    public void setServer(Server server) {}
}
