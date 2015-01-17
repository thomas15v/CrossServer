package com.thomas15v.crossserver.api.remote;

import com.thomas15v.crossserver.api.PayLoad;
import com.thomas15v.crossserver.api.util.ServerStatus;

import java.util.Collection;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface Server {

    String getName();

    Collection<Player> getPlayers();

    Player getPlayer(String player);

    void removePlayer(String player);

    void addPlayer(Player player);

    void broadcast(String string);

    ServerStatus getStatus();

    void setStatus(ServerStatus status);

    void ban(String player, String message);


    public void sendPayLoad(PayLoad payLoad, String service);
}
