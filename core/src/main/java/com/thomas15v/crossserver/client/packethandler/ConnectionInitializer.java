package com.thomas15v.crossserver.client.packethandler;

import com.thomas15v.crossserver.api.util.ConnectionStatus;
import com.thomas15v.crossserver.client.Client;
import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import com.thomas15v.crossserver.network.packet.server.PacketAuthentationResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class ConnectionInitializer extends PacketHandler {

    private ChannelWrapper cw;
    private String password = "123456";
    @NonNull
    private Client client;

    @Override
    public void handle(PacketAuthentationResult packet) {
        if (packet.getResult()) {
            System.out.println("successfully logged in");
            client.setStatus(ConnectionStatus.CONNECTED);
        }
        else {
            System.out.println("connection Failed");
            cw.disconnect();
            client.setStatus(ConnectionStatus.DISCONNECTED);
        }
    }

    @Override
    public void connected(ChannelWrapper cw) {
        this.cw = cw;
        System.out.println("Connected");
        client.setStatus(ConnectionStatus.AUTHORIZING);
        cw.sendPacket(new PacketLogin(client.getLocalServer().getName(), password));
    }

    @Override
    public void disconnected(ChannelWrapper cw) {
        System.out.println("Disconnected");
    }

}
