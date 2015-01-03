package com.thomas15v.crossserver.network.remote;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.network.ChannelWrapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class RemoteServer implements Server {

    @Getter
    @NonNull
    private String Name;

    private Map<String,Player> players = new HashMap<>();

    @Getter
    @Setter
    private ServerStatus status = ServerStatus.ONLINE;

    @NonNull
    @Getter
    @Setter
    private ChannelWrapper channel;

    @Override
    public Collection<Player> getPlayers() {
        return players.values();
    }

    @Override
    public Player getPlayer(String player) {
        return players.get(player);
    }

    @Override
    public void removePlayer(String player) {
        players.remove(player);
    }

    @Override
    public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

    @Override
    public void broadcast(String string) {

    }
}