package com.thomas15v.crossserver.api;

import java.util.Collection;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface Server {

    String getName();

    Collection<Player> getPlayers();

    void broadcast(String string);
}
