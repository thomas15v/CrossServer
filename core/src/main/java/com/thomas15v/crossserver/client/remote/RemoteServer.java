package com.thomas15v.crossserver.client.remote;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.ServerStatus;
import lombok.Getter;

import java.util.List;

/**
 * Created by thomas15v on 26/12/14.
 */
public class RemoteServer implements Server {

    @Getter
    private String Name;

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public Player getPlayer(String string) {
        return null;
    }

    @Override
    public void broadcast(String string) {

    }

    @Override
    public ServerStatus getStatus() {
        return null;
    }
}
