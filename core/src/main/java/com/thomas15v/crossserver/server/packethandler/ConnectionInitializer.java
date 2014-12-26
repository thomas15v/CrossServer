package com.thomas15v.crossserver.server.packethandler;

import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import com.thomas15v.crossserver.server.ConnectedServer;
import com.thomas15v.crossserver.server.CrossServer;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ConnectionInitializer implements PacketHandler {

    private String password = "123456";
    private CrossServer crossServer;
    private ChannelWrapper cw;

    public ConnectionInitializer(CrossServer crossServer){
        this.crossServer = crossServer;
    }

    @Override
    public void handle(Packet packet) {
        System.out.println("packet recived : " + packet);
    }

    @Override
    public void connected(ChannelWrapper cw) {
        this.cw = cw;
        System.out.println("Connected");
    }

    @Override
    public void disconnected(ChannelWrapper cw) {
        System.out.println("Disconnected");
    }

    public void handle(PacketLogin packet){
        if (packet.getPassword().equals(password))
            crossServer.getClients().put(packet.getServerName(), new ConnectedServer(packet.getServerName(), cw));

    }
}
