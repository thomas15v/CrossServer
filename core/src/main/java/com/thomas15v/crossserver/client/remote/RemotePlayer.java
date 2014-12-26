package com.thomas15v.crossserver.client.remote;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import lombok.Getter;

import java.util.UUID;

/**
 * Created by thomas15v on 26/12/14.
 */
public class RemotePlayer implements Player {

    @Getter
    private String name;
    @Getter
    private Server server;

    public RemotePlayer(String name, Server server){
        this.name = name;
        this.server = server;
    }


    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void kick() {

    }

    @Override
    public void ban() {

    }

    @Override
    public void sendMessage(String message) {

    }
}
