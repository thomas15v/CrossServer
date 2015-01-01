package com.thomas15v.crossserver.client.packethandler;

import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.server.PacketServerStatusChanged;
import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ClientHandler extends PacketHandler{


    @Override
    public void connected(ChannelWrapper cw) {

    }

    @Override
    public void disconnected(ChannelWrapper cw) {

    }

    @Override
    public void handle(PacketServerStatusChanged packet) {
        System.out.println(packet);
    }
}
