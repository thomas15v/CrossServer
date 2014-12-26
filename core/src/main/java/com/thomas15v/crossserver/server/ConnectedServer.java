package com.thomas15v.crossserver.server;

import com.thomas15v.crossserver.api.*;
import com.thomas15v.crossserver.network.ChannelWrapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Collection;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ConnectedServer implements com.thomas15v.crossserver.api.Server {

    @Getter
    public String name;
    public ChannelWrapper cw;

    public ConnectedServer(String name, ChannelWrapper cw) {
        this.name = name;
        this.cw = cw;
    }

    @Override
    public Collection<Player> getPlayers() {
        return null;
    }

    @Override
    public void broadcast(String string) {

    }
}
