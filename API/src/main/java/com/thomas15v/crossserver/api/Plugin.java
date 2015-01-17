package com.thomas15v.crossserver.api;

import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Server;

/**
 * Created by thomas15v on 25/12/14.
 */
public interface Plugin {

    Server getLocalServer();

    String getPassword();

    String getServerAdress();

    int getServerPort();

    void execute(Runnable task);

    CrossServer getCrossServer();
}
