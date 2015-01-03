package com.thomas15v.crossserver.api.remote;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.util.ConnectionStatus;
import com.thomas15v.crossserver.api.util.PlayerStatus;

import java.util.Collection;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface CrossServer extends Runnable {

    Collection<Server> getServers();

    Server getServer(String server);

    ConnectionStatus getStatus();

    void setStatus(ConnectionStatus status);

    Plugin getPlugin();

    void reportPlayerStatus(Player player, PlayerStatus playerStatus);

    void stop();

    public void broadcast(String message);
}
