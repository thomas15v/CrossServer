package com.thomas15v.crossserver.api.remote;

import com.thomas15v.crossserver.api.util.ConnectionStatus;

import java.util.List;

/**
 * Created by thomas15v on 26/12/14.
 */
public interface CrossServer {

    List<Server> getServers();

    ConnectionStatus getStatus();

    void setStatus(ConnectionStatus status);

    Server getLocalServer();

}
