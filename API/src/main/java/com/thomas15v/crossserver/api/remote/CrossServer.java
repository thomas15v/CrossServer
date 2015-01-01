package com.thomas15v.crossserver.api.remote;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.util.ConnectionStatus;
import com.thomas15v.crossserver.api.util.PlayerStatus;

import java.util.List;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface CrossServer extends Runnable {

    List<Server> getServers();

    ConnectionStatus getStatus();

    void setStatus(ConnectionStatus status);

    Plugin getPlugin();

    void reportPlayerStatus(Player player, PlayerStatus playerStatus);

}
