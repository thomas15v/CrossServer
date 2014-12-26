package com.thomas15v.crossserver.api.remote;

import com.thomas15v.crossserver.api.util.ServerStatus;

import java.util.List;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface Server {

    String getName();

    List<Player> getPlayers();

    void broadcast(String string);

    ServerStatus getStatus();
}
