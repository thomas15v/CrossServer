package com.thomas15v.crossserver.api.event.events.server;

import com.thomas15v.crossserver.api.remote.Server;

/**
 * Created by thomas15v on 16/01/15.
 */
public class ServerShutDownEvent extends ServerEvent {
    public ServerShutDownEvent(Server server) {
        super(server);
    }
}
