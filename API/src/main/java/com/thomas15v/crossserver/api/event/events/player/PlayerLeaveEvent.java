package com.thomas15v.crossserver.api.event.events.player;

import com.thomas15v.crossserver.api.remote.Player;

/**
 * Created by thomas15v on 16/01/15.
 */
public class PlayerLeaveEvent extends PlayerEvent {

    public PlayerLeaveEvent(Player player) {
        super(player);
    }
}
