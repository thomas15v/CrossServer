package com.thomas15v.crossserver.api.remote.nullremotes;

import com.thomas15v.crossserver.api.PayLoad;
import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.ServerStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by thomas15v on 19/01/15.
 */
@RequiredArgsConstructor
public class NullServer implements Server {

    @Getter
    @NonNull
    private String name;

    @Override
    public Collection<Player> getPlayers() {
        return new ArrayList<>();
    }

    @Override
    public Player getPlayer(String player) {
        return new NullPlayer(player);
    }

    @Override
    public void removePlayer(String player) {}

    @Override
    public void addPlayer(Player player) {}

    @Override
    public void broadcast(String string) {}

    @Override
    public ServerStatus getStatus() {
        return ServerStatus.OFFLINE;
    }

    @Override
    public void setStatus(ServerStatus status) {}

    @Override
    public void ban(String player, String message) {}

    @Override
    public void sendPayLoad(PayLoad payLoad, String service) {}
}
