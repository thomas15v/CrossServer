package com.thomas15v.crossserver.network.remote;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Created by thomas15v on 3/01/15.
 */
@RequiredArgsConstructor
public class NullPlayer implements Player {

    @NonNull
    @Getter
    private String name;

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void kick(String kickMessage) {
        System.out.println("Tried to kick offline player " + getName());
    }

    @Override
    public void ban(String banMessage) {
        System.out.println("Tried to ban offline player " + getName());
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Tried to send message; " + message + "; to offline player: " + getName());
    }

    @Override
    public Server getServer() {
        return null;
    }

    @Override
    public void setServer(Server server) {

    }
}
