package com.thomas15v.crossserver.network.remote;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class RemotePlayer implements Player {

    @NonNull
    @Getter
    private String name;
    @Getter
    @Setter
    private Server server;

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void kick(String kickMessage) {

    }

    @Override
    public void ban(String banMessage) {

    }

    @Override
    public void sendMessage(String message) {

    }
}
