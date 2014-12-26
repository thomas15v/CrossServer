package com.thomas15v.crossserver.server;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.network.ChannelWrapper;
import lombok.Getter;

import java.util.List;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ConnectedServer implements Server {

    @Getter
    public String name;
    @Getter
    public ChannelWrapper channel;
    @Getter
    private ServerStatus status;

    public ConnectedServer(String name, ChannelWrapper channel, ServerStatus status) {
        this.name = name;
        this.channel = channel;
        this.status = status;
    }

    public ConnectedServer(String name, ChannelWrapper channel) {
        this(name, channel, ServerStatus.ONLINE);
    }

    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public void broadcast(String string) {

    }
}
