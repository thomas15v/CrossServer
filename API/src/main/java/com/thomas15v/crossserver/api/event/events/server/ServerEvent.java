package com.thomas15v.crossserver.api.event.events.server;

import com.thomas15v.crossserver.api.event.events.Event;
import com.thomas15v.crossserver.api.remote.Server;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by thomas15v on 16/01/15.
 */
@RequiredArgsConstructor
public abstract class ServerEvent implements Event {

    @NonNull
    @Getter
    private Server server;

}
