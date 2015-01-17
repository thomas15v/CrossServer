package com.thomas15v.crossserver.api.event.events.player;

import com.thomas15v.crossserver.api.event.events.Event;
import com.thomas15v.crossserver.api.remote.Player;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by thomas15v on 16/01/15.
 */
@RequiredArgsConstructor
public abstract class PlayerEvent implements Event {

    @NonNull
    @Getter
    private Player player;

}
