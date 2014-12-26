package com.thomas15v.crossserver.client.packethandler;

import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ConnectionInitializer implements PacketHandler {

    private ChannelWrapper cw;
    private String name = "Towny_Server";
    private String password = "123456";

    @Override
    public void handle(Packet packet) {
        System.out.println("packet recived : " + packet);
    }

    @Override
    public void connected(ChannelWrapper cw) {
        this.cw = cw;
        System.out.println("Connected");
        cw.sendPacket(new PacketLogin(name, password));
    }

    @Override
    public void disconnected(ChannelWrapper cw) {
        System.out.println("Disconnected");
    }

}
