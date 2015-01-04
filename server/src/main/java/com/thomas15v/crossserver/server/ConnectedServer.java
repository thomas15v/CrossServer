package com.thomas15v.crossserver.server;

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
import java.util.Map;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class ConnectedServer implements Server {

    @Getter
    @NonNull
    public String name;
    @Getter
    @NonNull
    public ChannelWrapper channel;
    @Getter
    @Setter
    @NonNull
    private ServerStatus status;

    private Map<String, Player> players = new HashMap<>();

    public ConnectedServer(String name, ChannelWrapper channel) {
        this(name, channel, ServerStatus.ONLINE);
    }

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
    public void broadcast(String string) {}

    @Override
    public void ban(String player, String message) {

    }

    public void disconnect(){
        channel.disconnect();
    }
}
